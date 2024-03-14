package com.example.theghinho.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.theghinho.Models.Card;

import java.util.ArrayList;

public class TheGhiNhoOpenHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "theghinho.db";
    private static final int DATABASE_VERSION = 1;

    // Câu lệnh khởi tạo Database.
    private static final String CREATE_User = "create table "
            + "User" + "(" + "UserName text primary key," + " FName text not null," + "LName text not null,"
            + "Email text ," + "Password text not null," +
            "RoleId integer not null," + "FOREIGN KEY(RoleId) REFERENCES Role(RoleId)" + ");"
            ;
    private static final String CREATE_ROLE = "Create table Role (\n" +
            "RoleId integer primary key autoincrement,\n" +
            "RoleName text not null\n" +
            ");";
    private  static final String CREATE_FOLDER = "Create table Folder(\n" +
            "FolderId integer primary key autoincrement,\n" +
            "FolderName text not null,\n" +
            "UserId integer not null,\n" +
            "FOREIGN KEY (UserId) REFERENCES User(Id)\n" +
            ");\n";
    private static final String CREATE_CARD = "Create table Card(\n" +
            "CardId  integer primary key autoincrement,\n" +
            "FontCard text not null,\n" +
            "BackCard text not null,\n" +
            "FolderId integer not null,\n" +
            "FOREIGN KEY(FolderId) REFERENCES Folder(FolderId)\n" +
            "\n" +
            ");\n";
    private static final String CREATE_LEARNING_LOG = "Create table LearningLog(\n" +
            "\tLogId integer primary key autoincrement,\n" +
            "\tCardId int not null,\n" +
            "\tDate text not null,\n" +
            "\tDateLearn text not null,\n" +
            "\tInterval integer not null,\n" +
            "\tRepeatTime integer not null,\n" +
            "\tEF integer not null,\n" +
            "\tIsLearned integer not null,\n" +
            "\tFolderId intefer not null,\n" +
            "\tFOREIGN KEY (CardId) REFERENCES Card(CardId),\n" +
            "FOREIGN KEY (FolderId) REFERENCES Folder(FolderId));";

    private static final String CREATE_TEST = "Create table Test(\n" +
            "   TestId integer primary key autoincrement,\n" +
            "   TestName text not null,\n" +
            "   Date text not null,\n" +
            "   FolderId integer not null,\n" +
            "FOREIGN KEY (FolderId) REFERENCES Folder(FolderId));";
    private static final String CREATE_TEST_DETAIL = "Create table TestDetail(\n" +
            "   TestDetailId integer primary key autoincrement,\n" +
            "  CardId integer not null,\n" +
            "  Op1 text not null,\n" +
            "  \n" +
            "  Op2 text not null,\n" +
            "  Op3 text not null,\n" +
            "  CheckedOp text not null,\n" +
            "  TestId integer not null,\n" +
            " FOREIGN KEY (TestId) REFERENCES Test(TestId));\n";
    private static final String InsertRole = "Insert into Role (RoleName) values (Learner)";
    public TheGhiNhoOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ROLE);
        db.execSQL(CREATE_User);
        db.execSQL(CREATE_FOLDER);
        db.execSQL(CREATE_CARD);
        db.execSQL(CREATE_LEARNING_LOG);
        db.execSQL(CREATE_TEST);
        db.execSQL(CREATE_TEST_DETAIL);
        db.execSQL(InsertRole);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table User";
        db.execSQL(sql);
    }

    public void insertNewWord(String name) {
        String sql = "INSERT INTO Role (RoleName)\n" +
                "VALUES ( ?)\n";
        getWritableDatabase().execSQL(sql, new Object[]{name});
    }
    public Cursor getAllRoles(){
        String sql = "Select * from Role";
       return getReadableDatabase().rawQuery(sql,new String[]{});
    }
    public ArrayList<Card> getAllCards(){
        ArrayList<Card> cards = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select CardId, FontCard ,BackCard from Card", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int cardId = cursor.getInt(0);
            String fontCard = cursor.getString(1);
            String backCard = cursor.getString(2);
            cards.add(new Card(cardId, fontCard, backCard));
            cursor.moveToNext();

        }
        cursor.close();
        return cards;
    }
}
