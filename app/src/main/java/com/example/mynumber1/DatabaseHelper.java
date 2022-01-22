package com.example.mynumber1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mynum.db";
    public static final String TABLE_NAME = "mobile_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "title";
    public static final String COL_3 = "detail";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db, 1, 2);

        insertData("95", "คำทำนายของตัวเลข ๙๕");
        insertData("59", "คำทำนายของตัวเลข ๕๙");
        insertData("25", "คำทำนายของตัวเลข ๒๕");
        insertData("52", "คำทำนายของตัวเลข ๕๒");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "( ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TITLE TEXT, DETAIL TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String title, String detail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, title);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getData(String title) {
        String strSQL = "select * from " + TABLE_NAME + " WHERE TITLE='"+ title +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(strSQL, null);
        return res;

    }
}
