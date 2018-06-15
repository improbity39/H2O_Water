package com.example.user.page2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

import static android.widget.Toast.*;
import static com.example.user.page2.page1.APP_DATA;

public class page2 extends AppCompatActivity {
    private Button btn_check;
    private EditText height;
    private EditText weight;
    private EditText age;
    private Button btn_girl;
    private Button btn_boy;
    public int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        Intent intent = getIntent();
        SharedPreferences pref = getApplication().getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
        btn_check = (Button)this.findViewById(R.id.btn_check);
        btn_boy = (Button)this.findViewById(R.id.btn_boy);
        btn_girl = (Button)this.findViewById(R.id.btn_girl);
        age = (EditText)this.findViewById(R.id.age);
        weight = (EditText)this.findViewById(R.id.weight);
        height = (EditText)this.findViewById(R.id.height);
        final SharedPreferences.Editor edit = pref.edit();
        final DecimalFormat mDecimalFormat = new DecimalFormat("#.00");
        btn_boy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 2;
                btn_boy.setBackgroundColor(Color.parseColor("#778899"));
                btn_girl.setBackgroundColor(Color.parseColor("#FFB6C1"));
            }
        });
        btn_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                btn_boy.setBackgroundColor(Color.parseColor("#B0E0E6"));
                btn_girl.setBackgroundColor(Color.parseColor("#778899"));
            }
        });

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( !("".equals(height.getText().toString())
                        || "".equals(weight.getText().toString())) )
                {
                    float weight1 = Float.parseFloat(weight.getEditableText().toString());
                    float height1 = Float.parseFloat(height.getEditableText().toString());
                    edit.putFloat("KEY_HIGHT",height1);
                    edit.putFloat("KEY_WEIGHT1",weight1);
                    edit.apply();
                    float bmi = weight1/(height1*height1/10000);
                    int AGE = Integer.parseInt(age.getEditableText().toString());
                    String bmi_str =mDecimalFormat.format(bmi);
                    if(flag==0){
                        Toast.makeText(page2.this,"Please choice sex", LENGTH_SHORT).show();
                    }
                    else if (flag == 1){
                        Toast.makeText(page2.this,"You are girl!",Toast.LENGTH_SHORT).show();
                    }
                    else if(flag == 2 ){
                        Toast.makeText(page2.this,"You are boy!",Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(page2.this,page3.class);
                    intent.putExtra("BMI_EXTRA",bmi_str);
                    intent.putExtra("AGE_EXTRA",AGE);
                    startActivity(intent);

                }
            }
        });
    }

}
