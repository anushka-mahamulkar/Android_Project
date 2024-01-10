package com.example.jewelapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class helper extends SQLiteOpenHelper {
    public static final String dbname="database.db";
    public helper(@Nullable Context context) {
        super(context, "database.db", null, 1);
    }

    SQLiteDatabase database=this.getWritableDatabase();
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table registeruser(id integer Primary Key Autoincrement,name text,email text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("drop table if exists users");
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase Mydatabase = this.getWritableDatabase();
        Cursor cursor = Mydatabase.rawQuery("Select * from registeruser where email=?",new String[]{email});

        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean insertData(String email, String password, String name){
        SQLiteDatabase Mydatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("name",name);
        long result = Mydatabase.insert("registeruser",null,contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase Mydatabase = this.getWritableDatabase();
        Cursor cursor = Mydatabase.rawQuery("Select * from registeruser where email=? and password=?",new String[]{email,password});

        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }
}
