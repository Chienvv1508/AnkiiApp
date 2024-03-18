package com.example.theghinho.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.theghinho.Model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardDAO {
    TheGhiNhoOpenHelper dbContext;
    SQLiteDatabase database;
    public CardDAO(Context context){
        dbContext = new TheGhiNhoOpenHelper(context);
    }
    public void openDataBase(){
        database = dbContext.getWritableDatabase();
    }
    public void close(){
        dbContext.close();
    }

    @SuppressLint("Range")
    public List<Card> GetAllCardsByFolderId(int id){
        database = dbContext.getReadableDatabase();
        String sql  = "Select * from Card where FolderId = ?";
        String arg1 = "" +id;
       Cursor c =  database.rawQuery(sql,new String[]{arg1});
       List<Card> listCard = new ArrayList<Card>();
       if(c.moveToFirst()){

           do{
               Card card = new Card();
               card.setCardId(c.getInt(0));
               card.setFontCard(c.getString(c.getColumnIndex("FontCard")));
               card.setBackCard(c.getString(c.getColumnIndex("BackCard")));
               card.setFolderId(id);
               listCard.add(card);


           }while (c.moveToFirst());

       }
       return listCard;
    }
}
