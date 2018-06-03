package com.example.user.page2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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
import java.util.List;


public class page4 extends Activity {

    List<String> list;
    ListView listview;
    List<Boolean> listShow; // 這個用來記錄哪幾個 item 是被打勾的
    private Button buton;
    private TextView tx;
    private static final int msgKey1 = 1;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        Intent intent = getIntent();
        buton = (Button)findViewById(R.id.btn_finish);
        listview = (ListView) findViewById(R.id.lt);
        tx = (TextView)findViewById(R.id.txv);
        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int day;
        if(dayOfWeek==0){
            day=0;
        }
        else
        {
            day = 7-dayOfWeek;
        }
        tx.setText("本週結束還剩:"+day+"天"+(24-hour)+"小時");
        Thread t = new Thread(runnable);
        t.start();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        CheckedTextView chkItem = (CheckedTextView) v.findViewById(R.id.checklist);
                        chkItem.setChecked(!chkItem.isChecked());
                        listShow.set(position, chkItem.isChecked());
            }
        });
        listShow = new ArrayList<Boolean>();
        list = new ArrayList<String>();
        for(int x=1;x<7;x++) {
            list.add("專案" + x);
            listShow.add(false);
        }
        ListAdapter adapterItem = new Adapter(this,list);
        listview.setAdapter(adapterItem);
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
                    Toast.makeText(page4.this, "還沒完成", Toast.LENGTH_SHORT).show();
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
                    int day;
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    if(dayOfWeek==0){
                        day=0;
                    }
                    else
                    {
                        day = 7-dayOfWeek;
                    }
                    tx.setText("本週結束還剩:"+day+"天"+(24-hour)+"小時");
                    break;
                default:
                    break;
            }
        }
    };
}
