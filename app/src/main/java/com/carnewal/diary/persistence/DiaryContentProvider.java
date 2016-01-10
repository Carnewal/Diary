package com.carnewal.diary.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Brecht on 10/01/2016.
 *
 * Make sure you make use of an UriMatcher to interpret the URI's.
 *
 */
public class DiaryContentProvider extends ContentProvider {

    private MyDB myDB;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static { //http://developer.android.com/reference/android/content/UriMatcher.html
        sURIMatcher.addURI(Const.CONTENT_PROVIDER_NAME, Const.CONTENT_PROVIDER_TABLE_NAME, Const.DIARY_ENTRIES_URI);
    }


    @Override
    public boolean onCreate() {
        myDB = new MyDB(getContext());
        if(myDB != null) {

            return true;
        }

        return false;
    }

    @Nullable
    @Override
    // projection = Which columns to return.
    // selection = WHERE clause.
    // selectionArgs = WHERE clause value substitution
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        switch (sURIMatcher.match(uri)) {
            case Const.DIARY_ENTRIES_URI: {
                return myDB.getDiaries();
            }
            default:
                throw new IllegalArgumentException("Error URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        switch (sURIMatcher.match(uri)) {
            case Const.DIARY_ENTRIES_URI: {
                return "vnd.android.cursor.dir/diaries";
                //vnd.android.cursor.item voor 1 item, remember remember
            }
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowId = myDB.insertDiary(values);
        if(rowId > 0) {
            Uri _uri = ContentUris.withAppendedId(Const.CONTENT_PROVIDER_URL_URI, rowId);
            getContext().getContentResolver().notifyChange(_uri, null);

            Toast.makeText(getContext(), "Blazijde #" + rowId + " werd toegevoegd.", Toast.LENGTH_SHORT).show();
            return _uri;
        } else {
            Toast.makeText(getContext(), "Kon rij niet invoegen.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
