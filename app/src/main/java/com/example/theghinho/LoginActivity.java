package com.example.theghinho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.theghinho.DAO.TheGhiNhoOpenHelper;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btnLogin;
    TheGhiNhoOpenHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.edtUserName1);
        password = (EditText) findViewById(R.id.edtPassword1);
        btnLogin = (Button) findViewById(R.id.btnLogin1);
        DB = new TheGhiNhoOpenHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = DB.checkUsernamePassword(user,pass);
                    if (checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this , HomePage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}