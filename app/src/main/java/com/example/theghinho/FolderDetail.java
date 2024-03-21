package com.example.theghinho;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.theghinho.Adapter.CardListAdapter;
import com.example.theghinho.DAO.CardDAO;
import com.example.theghinho.Model.Card;
import com.example.theghinho.Model.Folder;

import java.util.List;

public class FolderDetail extends AppCompatActivity {
    Folder f;
    TextView txt1;
    RecyclerView rcl;
    List<Card> cards;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_detail);
        receiveData();
        bindingView();
        bindindAction();








    }

    private void receiveData() {
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("folderDetail");
         f = (Folder) b.getSerializable("folder");
    }

    private void bindindAction() {
    }

    private void bindingView() {
        txt1 = findViewById(R.id.txtFolderDetail);
        txt1.setText("Các từ trong " +f.getFolderName());
        rcl = findViewById(R.id.rclItemCard);
        CardDAO dao = new CardDAO(getApplicationContext());
       cards =  dao.GetAllCardsByFolderId(f.getFolderId());
        CardListAdapter adapter = new CardListAdapter(cards, getApplicationContext());
        rcl.setAdapter(adapter);
        rcl.setLayoutManager( new LinearLayoutManager(this));


    }
}