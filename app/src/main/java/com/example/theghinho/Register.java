package com.example.theghinho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theghinho.DAO.UserDAO;
import com.example.theghinho.Model.User;
import com.example.theghinho.Validation.ValidationUser;

public class Register extends AppCompatActivity {
    private EditText edtUserName;
    private EditText edtPass;
    private EditText edtCf;
    private EditText edtHo;
    private EditText edtTen;
    private Button btnRegister;
    private TextView txtCheckTk;
    private TextView txtCheckMk;
    private TextView txtCheckMKcf;
    private TextView txtCheckHo;
    private TextView txtChecTen;
    private  boolean isValid = true;
    private  Button btnQuayLaiDangNhap;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindingView();
        bindingAction();
        userDAO = new UserDAO(this);
    }

    private void bindingAction() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDAO.openDataBase();
                isValid = true;
                if(!ValidationUser.validateUsername(edtUserName.getText().toString())|| userDAO.isUserNameExisted(edtUserName.getText().toString())){
                    isValid = false;
                    txtCheckTk.setText("Tài khoản không hợp lệ");
                    txtCheckTk.setTextColor(Color.RED);
                    txtCheckTk.setVisibility(View.VISIBLE);
                }else {
                    txtCheckTk.setText("");

                }
                if(!ValidationUser.validatePass(edtPass.getText().toString())){
                    isValid = false;
                    txtCheckMk.setText("Mật khẩu không hợp lệ");
                    txtCheckMk.setTextColor(Color.RED);
                    txtCheckMk.setVisibility(View.VISIBLE);
                }else {
                    txtCheckMk.setText("");

                    if(edtCf.getText().toString().isEmpty()){
                        isValid = false;
                        txtCheckMKcf.setText("Yêu cầu nhập lại mật khẩu");
                        txtCheckMKcf.setTextColor(Color.RED);
                        txtCheckMKcf.setVisibility(View.VISIBLE);
                    }else {
                        if(edtCf.getText().toString().compareTo(edtPass.getText().toString()) != 0){
                            isValid = false;
                            txtCheckMKcf.setText("Nhập lại mật khẩu");
                            txtCheckMKcf.setTextColor(Color.RED);
                            txtCheckMKcf.setVisibility(View.VISIBLE);
                        }else {
                            txtCheckMKcf.setText("");


                        }

                    }
                }
                if(!ValidationUser.validateName(edtHo.getText().toString())){
                    isValid = false;
                    txtCheckHo.setText("Họ không hợp lệ");
                    txtCheckHo.setTextColor(Color.RED);
                    txtCheckHo.setVisibility(View.VISIBLE);
                }else {
                    txtCheckHo.setText("");
                    }
                if(!ValidationUser.validateName(edtTen.getText().toString())){
                    isValid = false;
                    txtChecTen.setText("Họ không hợp lệ");
                    txtChecTen.setTextColor(Color.RED);
                    txtChecTen.setVisibility(View.VISIBLE);
                }else {
                    txtChecTen.setText("");
                    }

                if(isValid){
                    User user = new User();
                    user.setUserName(edtUserName.getText().toString());
                    user.setPassword(edtPass.getText().toString());
                    user.setFName(edtTen.getText().toString());
                    user.setLname(edtHo.getText().toString());
                    userDAO.insertUser(user);
                    edtUserName.setText("");
                    edtPass.setText("");
                   edtCf.setText("");
                    edtHo.setText("");
                    edtTen.setText("");
                    Toast.makeText(getApplicationContext(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
                    userDAO.close();


                }


            }
        });
        btnQuayLaiDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i
                );


            }
        });
    }

    private void bindingView() {
        edtUserName = findViewById(R.id.edtUserNameR);
        edtPass = findViewById(R.id.edtPassR);
        edtCf = findViewById(R.id.edtCfR);
        edtHo = findViewById(R.id.edtHoR);
        edtTen = findViewById(R.id.edtTenR);
        btnRegister = findViewById(R.id.btnRegister);
        txtCheckTk = findViewById(R.id.txtCheckTKR);
        txtCheckMk = findViewById(R.id.txtCheckMKR);
        txtCheckMKcf = findViewById(R.id.txtCheckNhapLai);
        txtCheckHo = findViewById(R.id.txtCheckHo);
        txtChecTen = findViewById(R.id.txtCheckTenR);
        btnQuayLaiDangNhap = findViewById(R.id.btnBackToLogin);
    }
}