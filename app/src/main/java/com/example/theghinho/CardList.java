package com.example.theghinho;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theghinho.DAO.TheGhiNhoOpenHelper;
import com.example.theghinho.Models.Card;

import java.util.ArrayList;
import java.util.List;

public class CardList extends AppCompatActivity {
    private RecyclerView rcv;
    TheGhiNhoOpenHelper db;
    private void bindingView(){
        rcv = findViewById(R.id.rcvCard);
        db = new TheGhiNhoOpenHelper(this);
    }
    private void bindingAction(){

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_list);
        bindingView();
        bindingAction();
        innitListData();
    }
    private void innitListData(){
        rcv.setAdapter(new CardAdapter(db.getAllCards()));
        rcv.setLayoutManager(new GridLayoutManager(CardList.this, 1));
    }
}
