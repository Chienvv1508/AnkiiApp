package com.example.theghinho;

import android.annotation.SuppressLint;
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

public class ForgotPassActivity extends AppCompatActivity {

    EditText username1;
    Button reset;
    TheGhiNhoOpenHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fogot_pass);
        username1 = (EditText) findViewById(R.id.edtUsername_reset);
        reset = (Button) findViewById(R.id.btnreset);
        DB = new TheGhiNhoOpenHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username1.getText().toString();
                Boolean checkuser = DB.checkUserName(user);
                if(checkuser == true)
                {
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ForgotPassActivity.this, "User doesn't exists", Toast.LENGTH_SHORT).show();
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