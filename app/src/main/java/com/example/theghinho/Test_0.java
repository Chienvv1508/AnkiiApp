package com.example.theghinho;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class Test_0 extends AppCompatActivity {

    TextView question;
    RadioButton s4,s1,s2,s3;
    Button submit;
    TabHost mytab;
    ListView lv;
    ArrayList<String> mylist ;
    ArrayAdapter<String> myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test0);

        addControll();
        addEvent();
    }

    private void addEvent() {
        submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView cauhoi = findViewById(R.id.question);
            String cauhoii= cauhoi.getText().toString();
            RadioButton ra1 = findViewById(R.id.ra1);
            String a1 = ra1.getText().toString();
            RadioButton ra2 = findViewById(R.id.ra2);
            String a2 = ra2.getText().toString();
            RadioButton ra3 = findViewById(R.id.ra3);
            String a3 = ra3.getText().toString();
            RadioButton ra4 = findViewById(R.id.ra4);
            String a4 = ra4.getText().toString();
            mylist.add("câu hỏi : " + cauhoii+ "; đáp án "+ "a1");
            myadapter.notifyDataSetChanged();

            int a =0;

            String b;
        }
    });
    }

    @SuppressLint("WrongViewCast")
    private void addControll() {

    question = findViewById(R.id.question);
    s1 = findViewById(R.id.ra1);
    s2 = findViewById(R.id.ra2);
    s3 = findViewById(R.id.ra3);
    s4 = findViewById(R.id.ra4);

    submit = findViewById(R.id.submit);

    lv = findViewById(R.id.lv);
    mylist = new ArrayList<>();
    myadapter = new ArrayAdapter<>(Test_0.this, R.layout.item,mylist);
    lv.setAdapter(myadapter);

    mytab = findViewById(R.id.mytab);
    mytab.setup();
    TabHost.TabSpec spec1,spec2;

    spec1 = mytab.newTabSpec("tab1");
    spec1.setContent(R.id.tab1);
    spec1.setIndicator("test");
    mytab.addTab(spec1);

    spec2 = mytab.newTabSpec("tab2");
    spec2.setContent(R.id.tab2);
    spec2.setIndicator("kết quả");
    mytab.addTab(spec2);




    }
}