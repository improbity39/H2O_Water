package com.example.user.page2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.user.page2.page1.APP_DATA;

public class page5 extends AppCompatActivity {
    protected EditText re_weight;
    protected Button cheak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);
        Intent intent = getIntent();
        SharedPreferences pref = getApplication().getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
        re_weight = (EditText)findViewById(R.id.re_weight);
        cheak = (Button)findViewById(R.id.re_sure);
        final SharedPreferences.Editor edit = pref.edit();

        cheak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(re_weight.getText().toString().equals(""))){
                    float weight = Float.parseFloat(re_weight.getEditableText().toString());
                    edit.putFloat("KEY_REWEIGHT",weight);
                    edit.apply();
                    Intent intent = new Intent(page5.this,page6.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(page5.this, "尚未填入體重", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
