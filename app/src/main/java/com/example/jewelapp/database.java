package com.example.jewelapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public static final String dbname="database.db";
    public database(@Nullable Context context) {
        super(context, "database.db", null, 1);

    }
    SQLiteDatabase database=this.getWritableDatabase();

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table registeruser(id integer Primary Key Autoincrement,name text,email text,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
