package com.example.theghinho.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.theghinho.Model.User;

public class UserDAO  {
    TheGhiNhoOpenHelper dbContext;
    SQLiteDatabase database;

    public UserDAO(Context context){
        dbContext = new TheGhiNhoOpenHelper(context);
    }
    public void openDataBase(){
        database = dbContext.getWritableDatabase();
    }
    public void close(){
        dbContext.close();
    }
    public boolean isUserNameExisted(String userName){
    Cursor cursor =  dbContext.getReadableDatabase().query("User",
                null,
                "UserName=?", new String[]{userName}, "", "", "");
    return cursor.moveToFirst();
    }

    public long insertUser(User user){
        ContentValues values = new ContentValues();
        values.put("UserName", user.getUserName());
        values.put("Password",user.getPassword());
        values.put("LName", user.getLname());
        values.put("FName", user.getFName());
        values.put("RoleId",1);

       return dbContext.getWritableDatabase().insert("User", null, values);

    }

}
