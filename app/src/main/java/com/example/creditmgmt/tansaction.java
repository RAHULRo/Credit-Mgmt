package com.example.creditmgmt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class tansaction extends AppCompatActivity {


    ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tansaction);

        l1 = findViewById(R.id.l1);

        final ArrayList<String> al = getIntent().getExtras().getStringArrayList("list");
        Bundle bundle = getIntent().getExtras();
        final String name = bundle.getString("name");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,al);
        l1.setAdapter(adapter);
        final credit_database db = new credit_database(this);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String to = al.get(i);
                String from = name;
                db.update(from,to);
                Toast.makeText(tansaction.this, "Credits sended Successfully!!!", Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(tansaction.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
