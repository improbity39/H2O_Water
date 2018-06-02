package com.example.user.page2;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.DataSetObserver;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class page4 extends Activity {

    List<String> list;
    ListView listview;
    List<Boolean> listShow; // 這個用來記錄哪幾個 item 是被打勾的
    private Button buton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        Intent intent = getIntent();
        buton = (Button)findViewById(R.id.btn_finish);
        listview = (ListView) findViewById(R.id.lt);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        CheckedTextView chkItem = (CheckedTextView) v.findViewById(R.id.checklist);
                        chkItem.setChecked(!chkItem.isChecked());
//                Toast.makeText(page4.this, "您點選了第 " + (position + 1) + " 項", Toast.LENGTH_SHORT).show();
                        listShow.set(position, chkItem.isChecked());
            }
        });
        listShow = new ArrayList<Boolean>();
        list = new ArrayList<String>();
        for(int x=1;x<7;x++) {
            list.add("專案" + x);
            listShow.add(true);
        }
        ListAdapter adapterItem = new com.example.user.page2.ListAdapter(this,list);
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
                    System.out.println(listShow.get(x));
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
}
