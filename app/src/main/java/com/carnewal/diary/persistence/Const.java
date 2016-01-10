package com.carnewal.diary.persistence;

import android.net.Uri;

/**
 * Created by Brecht on 10/01/2016.
 *
 * The String constants used by the db
 *
 */
public class Const {

    public static final String DATABASE_NAME = "diary.db";
    public static final int DATABASE_VERSION = 2;
    public static final String ENTRIES_TABLE_NAME = "entries";
    public static final String ENTRY_COLUMN_ID = "id";
    public static final String ENTRY_COLUMN_TITLE = "title";
    public static final String ENTRY_COLUMN_CONTENT = "content";
    public static final String ENTRY_COLUMN_RECORDDATE = "recordDate";

    public static final String EXTRA_ENTRY = "entry";
    public static final String EXTRA_MODE = "mode";
    public static final String MODE_VIEW = "view";
    public static final String MODE_EDIT = "edit";



    public static final String CONTENT_PROVIDER_TABLE_NAME = "diaries";
    public static final String CONTENT_PROVIDER_NAME = "com.carnewal.diary.persistence.DiaryContentProvider";
    public static final String CONTENT_PROVIDER_URL_STRING = "content://" + CONTENT_PROVIDER_NAME + "/" + CONTENT_PROVIDER_TABLE_NAME;
    public static final Uri CONTENT_PROVIDER_URL_URI =  Uri.parse(CONTENT_PROVIDER_URL_STRING);


    public static final int DIARY_ENTRIES_URI = 1;

}
