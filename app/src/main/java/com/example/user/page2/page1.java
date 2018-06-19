package com.example.user.page2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import static com.example.user.page2.R.menu.main;

public class page1 extends AppCompatActivity {
    protected Button start;
    protected Boolean cheak_first;
    public static final String APP_DATA = "com.my.package.app";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        Intent intent1 = getIntent();
        start = (Button) findViewById(R.id.btn_start);
        SharedPreferences pref = getApplication().getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        cheak_first = pref.getBoolean("KEY_FIRST",true);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//start button
                if(cheak_first == true){
                    editor.putBoolean("KEY_FIRST",false);
                    editor.apply();
                    Intent intent = new Intent(page1.this,page2.class);
                    startActivity(intent);
                } else if(cheak_first == false){
                    Intent intent = new Intent(page1.this,page3.class);
                    startActivity(intent);
                }

            }
        });
    }

}
