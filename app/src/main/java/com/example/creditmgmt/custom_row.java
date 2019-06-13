package com.example.creditmgmt;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class custom_row  extends ArrayAdapter {

    ArrayList<String> al;
    ArrayList<String> credits;
    Activity activity ;
    public custom_row(Activity activity, ArrayList<String> al, ArrayList<String> credits) {
        super(activity, R.layout.custom_row,al);
        this.credits=credits;
        this.activity = activity;
        this.al = al;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.custom_row,null);
        TextView t1 = v.findViewById(R.id.t1);
        TextView t2 = v.findViewById(R.id.t2);
        t1.setText(al.get(position));
        t2.setText(credits.get(position));
        return v;
    }
}
