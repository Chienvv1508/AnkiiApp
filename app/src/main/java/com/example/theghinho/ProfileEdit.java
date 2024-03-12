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
    private RadioGroup rdgGender;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private EditText edtPhone;
    private EditText edtEmail;
    private EditText edtAddress;
    private Button btnSave;
    private void BindingView(){
        edtFName.findViewById(R.id.edtFName);
        edtLName.findViewById(R.id.edtLName);
        rdgGender.findViewById(R.id.rdgGender);
        radioMale.findViewById(R.id.radioMale);
        radioFemale.findViewById(R.id.radioFemale);
        edtPhone.findViewById(R.id.edtPhone);
        edtEmail.findViewById(R.id.edtEmail);
        edtAddress.findViewById(R.id.edtAddress);
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
