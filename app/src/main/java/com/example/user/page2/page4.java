package com.example.user.page2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.user.page2.page1.APP_DATA;


public class page4 extends Activity {

    List<String> list;
    ListView listview;
    List<Boolean> listShow; // 這個用來記錄哪幾個 item 是被打勾的
    private Button buton;
    private TextView tx;
    private TextView bm;
    private static final int msgKey1 = 1;
    protected Boolean first_p4;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        SharedPreferences pref = getApplication().getSharedPreferences(APP_DATA, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();
        first_p4 = pref.getBoolean("KEY_FIRST_p4", true);
        Intent intent = getIntent();
        buton = (Button) findViewById(R.id.btn_finish);
        listview = (ListView) findViewById(R.id.lt);
        tx = (TextView) findViewById(R.id.txv);
        bm = (TextView) findViewById(R.id.tx_bmi);
        String bmi = intent.getStringExtra("BMI");
        listShow = new ArrayList<Boolean>();
        list = new ArrayList<String>();
        if (first_p4 == true) {
            editor.putBoolean("KEY_FIRST_p4", false);
            bm.setText("BMI:" + bmi);
            editor.putString("BMI", bmi);
            Random rand = new Random();
            int num = rand.nextInt(4) + 3;
            editor.putInt("rand",num);
            int[] index = new int[num];
            for (int i = 0; i < num; i++) {
                index[i] = i;
            }
            int count = 0, temp, change1, change2;
            while (count != 20) {
                change2 = rand.nextInt(num);
                change1 = rand.nextInt(num);
                temp = index[change1];
                index[change1] = index[change2];
                index[change2] = temp;
                count++;
            }
            String[] names = {""};
            for (int x = 0; x < num; x++) {
                list.add("專案" + (x+1));
                listShow.add(false);
                editor.putString("list"+x,list.get(x));
                editor.putBoolean("listshow"+x,listShow.get(x));
            }
            editor.apply();

        } else {
            bmi = pref.getString("BMI", null);
            int num_list = pref.getInt("rand",0);
            bm.setText("BMI:" + bmi);
            for(int i = 0;i<num_list;i++){
                list.add(pref.getString("list"+i,null));
                listShow.add(pref.getBoolean("listshow"+i,false));
            }

        }
        ListAdapter adapterItem = new Adapter(this, list,listShow);
        listview.setAdapter(adapterItem);
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        int day;
        if (dayOfWeek == 0) {
            day = 0;
        } else {
            day = 7 - dayOfWeek;
        }
        tx.setText("本週結束還剩:" + day + "天" + (24 - hour) + "小時" + (60 - minute) + "分");
        Thread t = new Thread(runnable);
        t.start();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                CheckedTextView chkItem = (CheckedTextView) v.findViewById(R.id.checklist);
                chkItem.setChecked(!chkItem.isChecked());
                listShow.set(position,chkItem.isChecked());
                editor.putBoolean("listshow"+position,chkItem.isChecked());
                editor.apply();
            }
        });
        /*if(first_p4 == true) {
            listShow = new ArrayList<Boolean>();
            list = new ArrayList<String>();
            Random rand = new Random();
            int num = rand.nextInt(4) + 3;
            int[] index = new int[num];
            for (int i = 0; i < num; i++) {
                index[i] = i;
            }
            int count = 0, temp, change1, change2;
            while (count != 20) {
                change2 = rand.nextInt(num);
                change1 = rand.nextInt(num);
                temp = index[change1];
                index[change1] = index[change2];
                index[change2] = temp;
                count++;
            }
            String[] names = {""};
            for (int x = 1; x <= num; x++) {
                list.add("專案" + x);
                listShow.add(false);
            }
            ListAdapter adapterItem = new Adapter(this, list);
            listview.setAdapter(adapterItem);
        }
        else{

        }*/
        buton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                int flag = 0;
                for(int x=0;x<listShow.size();x++)
                {
                    if(listShow.get(x)==false)
                    {
                        flag = 1;
                    }
                }
                if(flag == 1){
                    Toast.makeText(page4.this, "還沒做完哦", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(page4.this,page5.class);
                    startActivity(intent);
                }
            }
        });
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            do{
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (true);
        }
    };
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case msgKey1:
                    Calendar cal = Calendar.getInstance();
                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
                    int minute = cal.get(Calendar.MINUTE);
                    int day;
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    if(dayOfWeek==0){
                        day=0;
                    }
                    else
                    {
                        day = 7-dayOfWeek;
                    }
                    tx.setText("本週結束還剩:"+day+"天"+(24-hour)+"小時"+(60-minute)+"分");
                    if(day==0 && hour == 24 && minute == 60){
                        refresh();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    public void refresh() {

        onCreate(null);

    }
}
