package com.example.food_nutrition;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DemoAdapter extends BaseAdapter {

    private final ArrayList<HashMap<String, String>> list;
    ArrayList<String> output = new ArrayList<>();
    CheckBox checkBox;
    Context context;

    public DemoAdapter(Context context, ArrayList<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArrayList<String> output = new ArrayList<>();
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.test1,null);
            checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            checkBox.setTag(position);
            HashMap<String,String> s = list.get(position);
            String a = s.get("Name");
            checkBox.setText(a);
            //checkBox.setChecked((Boolean) map.get("isSelected"));
        }

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //map.put("isSelected",((CheckBox) v).isChecked());
                if (((CheckBox) v).isChecked()){
                    //output.add("name",u);
                    String s = ((CheckBox) v).getText().toString();
                    output.add(s);
                    Toast.makeText(((Activity) context), "Checked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }
}