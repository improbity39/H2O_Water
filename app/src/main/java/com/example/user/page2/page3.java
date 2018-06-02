package com.example.user.page2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class page3 extends AppCompatActivity {
    protected TextView edt_bmi;
    protected Button exit;
    protected Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);
        edt_bmi = (TextView)findViewById(R.id.edt_bmi);
        exit = (Button)findViewById(R.id.bt_exit);
        next = (Button)findViewById(R.id.bt_next);
        Intent intent = getIntent();
        String bmi = intent.getStringExtra("BMI_EXTRA");
        String age = intent.getStringExtra("AGE_EXTRA");
        exit.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
        next.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page3.this,page4.class);
                startActivity(intent);
            }
        });
    }
}
