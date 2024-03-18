package com.example.theghinho.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.theghinho.Model.Folder;

import java.util.ArrayList;
import java.util.List;

public class FolderDAO {
    TheGhiNhoOpenHelper dbContext;
    SQLiteDatabase database;
    public FolderDAO(Context context){
        dbContext = new TheGhiNhoOpenHelper(context);
    }
    public void openDataBase(){
        database = dbContext.getWritableDatabase();
    }
    public void close(){
        dbContext.close();
    }

    @SuppressLint("Range")
    public List<Folder> getAllFolderById(String username){
        openDataBase();
        String sql = "Select * from Folder where UserName = ? ";

        Cursor c = database.rawQuery(sql, new String[]{username});
        List<Folder> folders = new ArrayList<Folder>();
        if(c.moveToFirst()){
            do{
                Folder f = new Folder();
                f.setFolderId(c.getInt(c.getColumnIndex("FolderId")));
                f.setFolderName(c.getString(c.getColumnIndex("FolderName")));
               f.setUserName(c.getString(c.getColumnIndex("UserName")));
                folders.add(f);

            }
            while(c.moveToNext());
        }
       return  folders;
    }
}
