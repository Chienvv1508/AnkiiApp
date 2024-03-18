package com.example.theghinho;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.theghinho.Adapter.FolderListAdapter;
import com.example.theghinho.DAO.FolderDAO;
import com.example.theghinho.Model.Folder;

import java.io.Serializable;
import java.util.List;

public class HomePage extends AppCompatActivity {
    Button btnThemHome;
    private RecyclerView rcv;

    List<Folder> folderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bindingView();
        bindingAction();
        getFolders();
        initListWordView();
    }

    private void getFolders() {
        FolderDAO folderDAO = new FolderDAO(this);
    //    folderList = folderDAO.getAllFolderById(1);
    }

    private void initListWordView() {
        rcv.setAdapter(new FolderListAdapter(folderList,this));
        rcv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void bindingAction() {

    }

    private void bindingView() {
        btnThemHome = findViewById(R.id.btnThemHomePage);
        registerForContextMenu(btnThemHome);
        rcv = findViewById(R.id.rclBoTheHomePage);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.them_hompage,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.opThemThe){

            if(folderList.size() == 0)
                Toast.makeText(this, "Bạn chưa có bộ thẻ",Toast.LENGTH_LONG).show();
            else {
                Bundle bundle = new Bundle();
                bundle.putSerializable("folders", (Serializable) folderList);
                Intent sendAddCard = new Intent(this,AddCardToFolder.class);
                sendAddCard.putExtra("folderlist", bundle);
                startActivity(sendAddCard);
            }





            Toast.makeText(this, "Thêm Thẻ", Toast.LENGTH_SHORT).show();

        }else {

            Intent it = new Intent(HomePage.this, AddFolder.class);
            startActivity(it);
            Toast.makeText(this, "Thêm Bộ Thẻ", Toast.LENGTH_SHORT).show();
        }
        return  true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
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