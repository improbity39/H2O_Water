package com.example.user.page2;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import java.util.List;

public class Adapter extends BaseAdapter
{
    private Activity activity;
    private List<String> mList;
    private List<Boolean> tflist;

    private static LayoutInflater inflater = null;


    public Adapter(Activity a, List<String> list,List<Boolean> listshow)
    {
        activity = a;
        mList = list;
        tflist = listshow;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public int getCount()
    {
        return mList.size();
    }


    public Object getItem(int position)
    {
        return position;
    }


    public long getItemId(int position)
    {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if(convertView==null)
        {
            vi = inflater.inflate(R.layout.list, null);
        }

        CheckedTextView chkBshow = (CheckedTextView) vi.findViewById(R.id.checklist);

        chkBshow.setText(mList.get(position).toString());
        chkBshow.setChecked(tflist.get(position));

        return vi;
    }
}
