package com.example.theghinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {
    Button btnThemHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bindingView();
        bindingAction();
    }

    private void bindingAction() {

    }

    private void bindingView() {
        btnThemHome = findViewById(R.id.btnThemHomePage);
        registerForContextMenu(btnThemHome);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.them_hompage,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.opThemThe){
            Toast.makeText(this, "Thêm Thẻ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Thêm Bộ Thẻ", Toast.LENGTH_SHORT).show();
        }
        return  true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        Log.d("Sai","Ở đâu");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.opBoThe){
            Toast.makeText(this, "Bo The", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.opDuyetThe){
            Toast.makeText(this, "Duyet The", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.opThongKe){
            Toast.makeText(this, "Thong Ke", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Dang Xuat", Toast.LENGTH_SHORT).show();

        }




        return super.onOptionsItemSelected(item);
    }
}