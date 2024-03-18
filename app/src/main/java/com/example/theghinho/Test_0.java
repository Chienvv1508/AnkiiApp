package com.example.theghinho;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theghinho.DAO.TheGhiNhoOpenHelper;

import java.util.ArrayList;

public class Test_0 extends AppCompatActivity {

    TextView question,testcard_id;
    RadioButton ra1,ra2,ra3,ra4;
    Button submit;
    TabHost mytab;
    ListView lv;
    ArrayList<String> mylist ;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;

    TheGhiNhoOpenHelper Dbcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test0);
        Dbcontext = new TheGhiNhoOpenHelper(this);
        addControll();
        addEvent();
        nextEven();
        backEven();
    }

    private void backEven(){
        Button backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView testcard_id = findViewById(R.id.testcard_id);
                int testcard_idd = Integer.parseInt(testcard_id.getText().toString());

                getcard(testcard_idd-1);
            }
        });
    }
    private void nextEven() {
        Button nextbtn = findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView testcard_id = findViewById(R.id.testcard_id);
            int testcard_idd = Integer.parseInt(testcard_id.getText().toString());

            getcard(testcard_idd+1);
    }
});


    }
    private void getcard(int needid){

        // Mảng chứa các cột bạn muốn truy vấn
        String[] projection = { "*" };

// Điều kiện WHERE
        String selection = "CardId = ?";
        String[] selectionArgs = { String.valueOf(needid) }; // Giá trị thực sự của id bạn muốn truy vấn

// Thực hiện truy vấn
//        Cursor c = mydatabase.query(
//                "card",       // Tên bảng
//                null,   // Các cột bạn muốn truy vấn
//                null,    // Câu lệnh WHERE
//                null,// Giá trị của câu lệnh WHERE
//                null,         // Group by
//                null,         // Having
//                null          // Order by
//        );

        Cursor c = mydatabase.rawQuery("SELECT * FROM card WHERE " + selection, selectionArgs);

        if (c != null && c.moveToFirst()) {
            String id = c.getString(0);
            testcard_id.setText(id);
            String que = c.getString(1);
            question.setText(que);

            ra1.setText(c.getString(2));


            ra3.setText("đáp án nhiễu");
            ra4.setText("đáp án nhiễu");
        } if(c != null && c.moveToNext()){
            ra2.setText(c.getString(2));
        }
        c.close();
    }

    private void addEvent() {
        submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String dapan="";
            String op1="";
            String op2="";
            String op3="";
            TextView testcard_id = findViewById(R.id.testcard_id);
            String testcard_idd = testcard_id.getText().toString();
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

            if(ra1.isChecked()){
                dapan= a1;
                op1=a2;
                op2=a3;
                op3=a4;
            }
            if(ra2.isChecked()){
                dapan= a2;
                op1=a1;
                op2=a3;
                op3=a4;
            }
            if(ra3.isChecked()){
                dapan= a3;
                op1=a1;
                op2=a2;
                op3=a4;
            }
            if(ra4.isChecked()){
                dapan= a4;
                op1=a1;
                op2=a2;
                op3=a3;
            }

            mylist.add("câu hỏi : " + cauhoii+ "; đáp án "+ dapan);
            myadapter.notifyDataSetChanged();

            ContentValues myvalue = new ContentValues();
            myvalue.put("CardId",1);
            myvalue.put("Op1",op1);
            myvalue.put("Op2",op2);
            myvalue.put("Op3",op3);
            myvalue.put("CheckedOp", dapan);
            myvalue.put("TestId",1);
            String msg ="";

            mydatabase= Dbcontext.getWritableDatabase();
            if(mydatabase.insert("TestDetail",null,myvalue)==-1){
                msg = "fail";
                Log.d("test","fail");
            }else {
                msg= "sucssesfully";
                Log.d("test","sus");
            }
            Toast.makeText(Test_0.this,msg,Toast.LENGTH_SHORT).show();

        }
    });
    }

    @SuppressLint("WrongViewCast")
    private void addControll() {
    testcard_id = findViewById(R.id.testcard_id);
    question = findViewById(R.id.question);
    ra1 = findViewById(R.id.ra1);
    ra2 = findViewById(R.id.ra2);
    ra3 = findViewById(R.id.ra3);
    ra4 = findViewById(R.id.ra4);

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

    mydatabase = openOrCreateDatabase("theghinho.db",MODE_PRIVATE,null);

    getcard(1);

    }


}