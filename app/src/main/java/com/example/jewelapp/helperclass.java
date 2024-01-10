package com.example.jewelapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class
helperclass extends SQLiteOpenHelper {
    public static final String dbname="users.db";
    private byte[] byteimage;
    ByteArrayOutputStream byteArrayOutputStream;
    public helperclass(@Nullable Context context) {
        super(context," users.db", null, 1);
    }

    SQLiteDatabase database=this.getWritableDatabase();
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(id integer Primary key  Autoincrement,name text,email text,password text,image blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertUser(registerclass r1){
        SQLiteDatabase database1=this.getWritableDatabase();
        Bitmap bitmapimage = r1.getImage();
        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapimage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byteimage = byteArrayOutputStream.toByteArray();
        ContentValues cv=new ContentValues();
        cv.put("name",r1.getName());
        cv.put("email",r1.getEmail());
        cv.put("password",r1.getPassword());
        cv.put("image",byteimage);
        long res =database1.insert("users",null,cv);
        if(res==-1){
            return false;
        }else{
            return true;
        }

    }
    public Cursor viewRecords()//return type is cursor
    {
        SQLiteDatabase database=this.getReadableDatabase();// create new method
        Cursor res=database.rawQuery("select * from users",null); // showing record query
        return res;
    }
    public Cursor searchRecord(String email){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor res=database.rawQuery("select * from users where email="+email,null);
        return res;
    }
    public boolean updateUser(String id,String email,String password,String name)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("id",id);
        cv.put("name",name);
        cv.put("password",password);
        cv.put("email",email);

        long res=database.update("users",cv,"email=?",new String[]{id});
        if (res==1)
            return true;
        else
            return false;
    }

    public Cursor login(String email,String password){
        SQLiteDatabase database1=this.getReadableDatabase();
        Cursor res=database1.rawQuery("select * from users where email=? and password=?",new String[]{email,password});
        return res;
    }
}
