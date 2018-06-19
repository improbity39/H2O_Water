package com.example.user.page2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;

import static com.example.user.page2.page1.APP_DATA;

public class page3 extends AppCompatActivity {
    protected TextView edt_bmi;
    protected Button exit;
    protected Button next;
    private TextView txtProgress;
    private ProgressBar progressBar;
    private float pStatus = 0;
    private String bm;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        Intent intent = getIntent();
        String bmi = intent.getStringExtra("BMI_EXTRA");
        bm = bmi;
        SharedPreferences pref = getApplication().getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        String age = intent.getStringExtra("AGE_EXTRA");
        DecimalFormat df1 = new DecimalFormat("#.00");
        float weight1 = pref.getFloat("KEY_WEIGHT1",0);//+++
        float height = pref.getFloat("KEY_HIGHT",0);//+++
        Float bmi22 = weight1/(height*height/10000);//++++
        String bmi2  =df1.format(bmi22);//bmi22.toString(); //++++
         editor.putString("BMI",bmi2);
        editor.apply();
        txtProgress = (TextView) findViewById(R.id.txtProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //final Double a = Double.parseDouble(bmi);
        final Double a = Double.parseDouble(bmi2);//++++
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= (2.5)*a) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            float flag = pStatus;
                            progressBar.setProgress((int) pStatus);
                            Float bmi=flag;
                            DecimalFormat df = new DecimalFormat("#.0");
                            String bmi2 = df.format(bmi);
                            txtProgress.setText("BMI : " + df.format(Float.parseFloat(bmi2)*0.40));
                        }
                    });
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus+=0.1;
                }
            }
        }).start();
        edt_bmi = (TextView)findViewById(R.id.edt_bmi);
        exit = (Button)findViewById(R.id.bt_exit);
        next = (Button)findViewById(R.id.bt_next);
        edt_bmi.setText(bmi2);//++++++++++
        exit.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page3.this,page1.class);
                startActivity(intent);
//                moveTaskToBack(true);
//                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        next.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page3.this,page4.class);
                //intent.putExtra("BMI",bm);
                startActivity(intent);
            }
        });
    }
}
