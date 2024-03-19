package com.example.theghinho;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theghinho.DAO.TheGhiNhoOpenHelper;

public class ProfileEdit extends AppCompatActivity {

    private EditText edtFName;
    private EditText edtLName;
    private EditText edtUserName;

    private Button btnSave;
    SQLiteDatabase mydatabase;
    TheGhiNhoOpenHelper Dbcontext;

    private void BindingView(){
        edtFName= findViewById(R.id.edtFName);
        edtLName=findViewById(R.id.edtLName);

        edtUserName=findViewById(R.id.edtUserName);
        btnSave=findViewById(R.id.btnSave);

        mydatabase = openOrCreateDatabase("theghinho.db",MODE_PRIVATE,null);
        mydatabase= Dbcontext.getWritableDatabase();
// chưa có data mở ra để test
//        ContentValues myvalue = new ContentValues();
//
//        myvalue.put("UserName","1");
//        myvalue.put("FName","name");
//        myvalue.put("LName","lát");
//
//        myvalue.put("Password","123456");
//        myvalue.put("RoleId",1);
//        String msg ="";
//        mydatabase= Dbcontext.getWritableDatabase();
//        if(mydatabase.insert("User",null,myvalue)==-1){
//            msg = "fail";
//            Log.d("test","fail");
//        }else {
//            msg= "sucssesfully";
//            Log.d("test","sus");
//        }
//        Toast.makeText(ProfileEdit.this,msg,Toast.LENGTH_SHORT).show();

        // Mảng chứa các cột bạn muốn truy vấn

        getuser();
    }

    private void getuser(){
        String[] projection = { "*" };

        // Điều kiện WHERE
        String selection = "UserName = ?";
        Intent it = getIntent();
        String user = it.getStringExtra("username");
        String[] selectionArgs = { String.valueOf(user) }; // Giá trị thực sự của id bạn muốn truy vấn

        Cursor c = mydatabase.rawQuery("SELECT * FROM User WHERE " + selection, selectionArgs);



        if (c != null && c.moveToFirst()) {
            String ln = c.getString(2);
            edtLName.setText(c.getString(2));
            edtFName.setText(c.getString(1));
            edtUserName.setText(c.getString(0));

        }

        c.close();
    }
    private void BindingAction(){
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo đối tượng ContentValues để lưu các giá trị cần cập nhật
                ContentValues values = new ContentValues();

                values.put("FName",edtFName.getText().toString());

                values.put("LName", edtLName.getText().toString());

// Điều kiện WHERE
                String selection = "UserName = ?";
                String[] selectionArgs = { String.valueOf(edtUserName.getText()) }; // Thay id bằng giá trị của cột id bạn muốn cập nhật

// Thực hiện câu lệnh UPDATE
                int count = mydatabase.update("User", values, selection, selectionArgs); // Thay "table_name" bằng tên bảng bạn muốn cập nhật

                getuser();
                Log.d("edit","thành công"+ edtFName+edtLName);

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);
        Dbcontext = new TheGhiNhoOpenHelper(this);
        BindingView();
        BindingAction();
    }
}
