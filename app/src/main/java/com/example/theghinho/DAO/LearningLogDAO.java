package com.example.theghinho.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.theghinho.Model.LearningLog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        String isLearned = "0";

        Cursor c = database.rawQuery(sql,new String[]{folderId,formattedDate,isLearned});
        List<LearningLog> lisLearningLog = new ArrayList<LearningLog>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if(c.moveToFirst()){
            do{
                LearningLog learningLog = new LearningLog();
                learningLog.setLogId(c.getInt(c.getColumnIndex("LogId")));
                learningLog.setCardId(c.getInt(c.getColumnIndex("CardId")));


                try {

                    Date date11 = dateFormat.parse(c.getString(c.getColumnIndex("Date")));


                    learningLog.setDate(date11);
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

    public void updateIsLearned(int logId) {
        openDataBase();
        String sql = "Update LearningLog set IsLearned = ? where LogId = ?";
        database.execSQL(sql,new Object[]{"1",""+logId});
    }

    @SuppressLint("Range")
    public LearningLog addNewLearningLog(LearningLog log) {
        openDataBase();
        String sql2 = "Insert into LearningLog(CardId,Date,DateLearn,Interval," +
                "RepeatTime,EF,IsLearned,FolderId) values(?,?,?,?,?,?,?,?)";
        String sql = "select LogId from LearningLog order by LogId desc ";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");





        database.execSQL(sql2,new Object[]{log.getCardId(),dateFormat.format(log.getDate()),dateFormat.format(log.getDateLearn()),log.getInterval()
        ,log.getRepeatTime(),log.getEf(),0,log.getFolderId()});
        Cursor c= database.rawQuery(sql,new String[]{});
        LearningLog log1 = log;
        if(c.moveToFirst()){
            log1.setLogId(c.getInt(c.getColumnIndex("LogId")));
        }
        return log1;


    }
}
