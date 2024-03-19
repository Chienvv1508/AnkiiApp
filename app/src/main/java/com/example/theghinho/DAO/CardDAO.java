package com.example.theghinho.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.theghinho.Model.Card;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void addCard(Card c){
        openDataBase();
        String sql = "Insert into Card(FontCard,BackCard,FolderId) values(?,?,?)";
        String sql2 = "Insert into LearningLog(CardId,Date,DateLearn,Interval," +
                "RepeatTime,EF,IsLearning,FolderId) values(?,?,?,?,?,?,?,?)";
        int cardId = getCardId();

        String folderId = "" + c.getFolderId();
        database.execSQL(sql,new Object[]{c.getFontCard(),c.getBackCard(),folderId});

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);

        String cardId1 = ++cardId + "";
        database.execSQL(sql2,new Object[]{cardId1,formattedDate,formattedDate,0,0,0,0,folderId});



    }

    @SuppressLint("Range")
    private int getCardId() {
        openDataBase();
        String sql = "Select CardId from Card Order By CardId DESC";
        Cursor c = database.rawQuery(sql,new String[]{});
        int n = -1;
        if(c.moveToFirst()){
            n = c.getInt(c.getColumnIndex("CardId"));
        }

        return n;
    }
}
