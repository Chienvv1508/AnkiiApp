package com.example.theghinho.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.theghinho.Model.LearningLog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LearningLogDAO {
    TheGhiNhoOpenHelper dbContext;
    SQLiteDatabase database;

    public LearningLogDAO(Context context){
        dbContext = new TheGhiNhoOpenHelper(context);
    }
    public void openDataBase(){
        database = dbContext.getWritableDatabase();
    }
    public void close(){
        dbContext.close();
    }

    @SuppressLint("Range")
    public List<LearningLog> getAllCardIsNotLearnedInFolder(int id){
        openDataBase();
        String sql = "Select * from LearningLog where FolderId = ? and DateLearn = ? and Islearned = ?";
        String folderId = "" + id;
        LocalDate date = LocalDate.now();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(date);
        String isLearned = "false";

        Cursor c = database.rawQuery(sql,new String[]{folderId,formattedDate,isLearned});
        List<LearningLog> lisLearningLog = new ArrayList<LearningLog>();
        if(c.moveToFirst()){
            do{
                LearningLog learningLog = new LearningLog();
                learningLog.setLogId(c.getInt(c.getColumnIndex("LogId")));
                learningLog.setCardId(c.getInt(c.getColumnIndex("CardId")));


                try {
                    Date date1 = dateFormat.parse(c.getString(c.getColumnIndex("Date")));
                    learningLog.setDate(date1);
                }  catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Date date2 = dateFormat.parse(c.getString(c.getColumnIndex("DateLearn")));
                    learningLog.setDateLearn(date2);
                }  catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                learningLog.setInterval(c.getInt(c.getColumnIndex("Interval")));
                learningLog.setRepeatTime(c.getInt(c.getColumnIndex("RepeatTime")));
                learningLog.setEf(c.getFloat(c.getColumnIndex("EF")));
                learningLog.setFolderId(id);
                learningLog.setLearned(false);
                lisLearningLog.add(learningLog);




            }
            while (c.moveToNext());
        }
        return lisLearningLog;

    }
}
