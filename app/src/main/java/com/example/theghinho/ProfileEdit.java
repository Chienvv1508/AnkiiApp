package com.example.theghinho;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.theghinho.DAO.TheGhiNhoOpenHelper;

public class ProfileEdit extends AppCompatActivity {

    private TheGhiNhoOpenHelper dbConText;
    private EditText edtFName;
    private EditText edtLName;
    private EditText edtUserName;
    private EditText edtEmail;
    private Button btnSave;
    private void BindingView(){
        edtFName.findViewById(R.id.edtFName);
        edtLName.findViewById(R.id.edtLName);
        edtUserName.findViewById(R.id.edtUserName);
        edtEmail.findViewById(R.id.edtEmail);
        btnSave.findViewById(R.id.btnSave);
    }
    private void BindingAction(){
        btnSave.setOnClickListener(this::onBtnSaveClick);
    }

    private void onBtnSaveClick(View view) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);
        BindingView();
        BindingAction();
        dbConText = new TheGhiNhoOpenHelper(this);
    }
}
