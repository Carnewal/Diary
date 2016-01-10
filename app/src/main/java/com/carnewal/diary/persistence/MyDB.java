package com.carnewal.diary.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Brecht on 10/01/2016.
 *
 * MyDB.java : contains the SQLLitedatabase and a MyDBHelper class
 *
 */
public class MyDB {


    private static final String TAG = "MyDB.class";

    private SQLiteDatabase db;
    private MyDBHelper helper;


    public MyDB(Context context) {
        helper = new MyDBHelper(context);


    }

    public void open(){
        db = helper.getWritableDatabase();
        {
            db = helper.getReadableDatabase();
            Log.i(TAG, "Readable database instead of writable");
        }
    }

    public void close() {
        db.close();
    }

    public long insertDiary(ContentValues newDiaryValue){
        db = helper.getWritableDatabase();
        return db.insert(Const.ENTRIES_TABLE_NAME, null, newDiaryValue);

    }

    public Cursor getDiaries(){
        db = helper.getReadableDatabase();
        Cursor c =  db.query(Const.ENTRIES_TABLE_NAME, null, null, null, null, null, null);
        return c;
    }




}
