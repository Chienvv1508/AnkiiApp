package com.example.theghinho;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.theghinho.DAO.TheGhiNhoOpenHelper;

public class MainActivity extends AppCompatActivity {
    TheGhiNhoOpenHelper dbConText;
    Button bntS, btnL;
    EditText edtName;
    TheGhiNhoOpenHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bindingView();
        bindingAction();

        dbConText = new TheGhiNhoOpenHelper(this);
    }

    private void bindingAction() {
        bntS.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                 db.insertNewWord(name);
            }
        });
        btnL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.getAllRoles();
                if (c.moveToFirst()) {
                    do {
                        @SuppressLint("Range") String id = c.getString(c.getColumnIndex("RoleId"));

                        @SuppressLint("Range") String roleName = c.getString(c.getColumnIndex("RoleName"));

                        Log.d("Id"+id+ ": " , "Name: " + roleName);

                    } while (c.moveToNext());
                }
            }
        });
    }

    private void bindingView() {
        bntS = findViewById(R.id.btnSave);
        btnL = findViewById(R.id.btnLoad);
        edtName = findViewById(R.id.edtName);
        db = new TheGhiNhoOpenHelper(this);


        setContentView(R.layout.forgotpassword);


    }
}