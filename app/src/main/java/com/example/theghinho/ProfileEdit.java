package com.example.theghinho;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileEdit extends AppCompatActivity {

    private EditText edtFName;
    private EditText edtLName;
    private EditText edtUserName;
    private EditText edtEmail;
    private Button btnSave;
    private void BindingView(){
        edtFName.findViewById(R.id.edtFName);
        edtLName.findViewById(R.id.edtLName);
        edtEmail.findViewById(R.id.edtEmail);
        edtUserName.findViewById(R.id.edtUserName);
        btnSave.findViewById(R.id.btnSave);
    }
    private void BindingAction(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);
        BindingView();
        BindingAction();
    }
}
