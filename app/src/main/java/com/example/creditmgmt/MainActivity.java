package com.example.creditmgmt;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView l1;
    int pos;
    String st;
    Dialog dialog;
    ArrayList<String> credits;
    ArrayList<String> al;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1 = findViewById(R.id.l1);
        final String[] arr = {"ram","shyam","rahul","abhi","manoj","vishal","roy","raja","kaka","maya","kiran"};


        credit_database cdb = new credit_database(this);
        database db = new database(this);
        for(int i=0;i<arr.length;i++) {
            db.add(arr[i]);
            long val = cdb.addcredits(arr[i],""+arr[i]+"@gmail.com");
        }


         al = db.getall();



        credits = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            int val = cdb.getcredits(arr[i]);
            credits.add(val+"");

        }


        custom_row cus = new custom_row(this,al,credits);
        l1.setAdapter(cus);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos=i;
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialogbox);
                final TextView t1 = dialog.findViewById(R.id.t1);
                final TextView t2 = dialog.findViewById(R.id.t2);
                final TextView t3 = dialog.findViewById(R.id.t3);
                final Button b1 = dialog.findViewById(R.id.b1);
                t1.setText(arr[i]);
                t2.setText(arr[i]+"@gmail.com");
                int val = Integer.parseInt(credits.get(i));
                t3.setText(credits.get(i));
                dialog.show();
                if(val<0){
                    b1.setEnabled(false);
                }
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(MainActivity.this, tansaction.class);
                        intent.putStringArrayListExtra("list", al);
                        intent.putExtra("name",arr[pos]);
                        startActivity(intent);

                    }
                });
            }
        });



        db.delete(this);


    }


}
