package com.example.theghinho.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.theghinho.Model.Folder;
import com.example.theghinho.Model.User;

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
    public long insertFolder(Folder folder, String user) {
        ContentValues values = new ContentValues();
        values.put("FolderName",folder.getFolderName());
        values.put("UserName", user);

        return dbContext.getWritableDatabase().insert("Folder", null, values);

    }

    public boolean checkFolderByFoldeNameExisted(String name) {
        openDataBase();
        String sql = "select * from Folder where FolderName = ?";
        Cursor c = database.rawQuery(sql,new String[]{name});
        return  c.moveToFirst();

    }
}
