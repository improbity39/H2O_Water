package com.example.user.page2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static com.example.user.page2.page1.APP_DATA;

public class page6 extends AppCompatActivity {
    protected TextView weight_Before;
    protected TextView weight_After;
    protected TextView difference;
    protected Button exit;
    private EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int[] use = {0};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page6);
        final Intent intent = getIntent();
        SharedPreferences pref = getApplication().getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
        final DecimalFormat mDecimalFormat = new DecimalFormat("#.00");
        final SharedPreferences.Editor edit = pref.edit();
        weight_Before = (TextView)findViewById(R.id.tv_beforeWeekW);
        weight_After = (TextView)findViewById(R.id.tv_afterWeekW);
        difference = (TextView)findViewById(R.id.tv_difference);
        exit = (Button)findViewById(R.id.bt_sentAndExit);
        float weight1 = pref.getFloat("KEY_WEIGHT1",0);
        final float weight2 = pref.getFloat("KEY_REWEIGHT",0);
        float height = pref.getFloat("KEY_HIGHT",0);
        float bmi = weight2/(height*height/10000);
        String bmi_str = mDecimalFormat.format(bmi);
        edit.putString("BMI",bmi_str);
        edit.apply();
        float weight_d = weight2-weight1;
        String weight1toS = String.valueOf(weight1);
        String weight2toS = String.valueOf(weight2);
        String weight_dtoS = String.valueOf(weight_d);
        weight_Before.setText(weight1toS);
        weight_After.setText(weight2toS);
        difference.setText(weight_dtoS);
        Button startBtn = (Button) findViewById(R.id.sendEmail);//++++++
        startBtn.setOnClickListener(new Button.OnClickListener() {//++++++++
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        exit.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                edit.putFloat("KEY_WEIGHT1",weight2);
                edit.apply();
                Intent intent1 = new Intent(page6.this,page1.class);
                startActivity(intent1);
            }
        });
    }

    protected void sendEmail() {//++++++++++++
        Log.i("Send email", "");

        String[] TO = {"ask5890111@yahoo.com.tw"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        edt=(EditText)this.findViewById(R.id.commit_email);
        String message = edt.getText().toString();
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "意見");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //finish();
            edt.setText("");
            Log.i("Finish sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(page6.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
