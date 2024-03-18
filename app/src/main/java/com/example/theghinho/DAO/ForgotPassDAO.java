package com.example.theghinho.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ForgotPassDAO {
    TheGhiNhoOpenHelper dbContext;
    SQLiteDatabase database;
    public ForgotPassDAO(Context context){
        dbContext = new TheGhiNhoOpenHelper(context);
    }
    public void openDataBase(){
        database = dbContext.getWritableDatabase();
    }
    public void close(){
        dbContext.close();
    }

    public Boolean updatepassword(String username, String password)
    {
        SQLiteDatabase MyDB = this.dbContext.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        long result = MyDB.update("User", contentValues, "username = ? ", new String[] {username});
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }
}
