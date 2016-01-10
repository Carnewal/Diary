package com.carnewal.diary.persistence;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Brecht on 10/01/2016.
 *
 * MyDBHelper.java : an extention of SQLiteOpenHelper which will manage the database
 *
 */
public class MyDBHelper extends SQLiteOpenHelper {

    private final String CREATE_QUERY = "CREATE TABLE " + Const.ENTRIES_TABLE_NAME + " (" +
            Const.ENTRY_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Const.ENTRY_COLUMN_TITLE + " TEXT NOT NULL, " +
            Const.ENTRY_COLUMN_CONTENT + " TEXT NOT NULL, " +
            Const.ENTRY_COLUMN_RECORDDATE + " LONG" +
            ");";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public MyDBHelper(Context context) {
        super(context, Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Del", "Drop");
        db.execSQL("DROP TABLE IF EXISTS " + Const.ENTRIES_TABLE_NAME);
        onCreate(db);
    }
}
