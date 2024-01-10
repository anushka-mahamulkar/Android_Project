package com.example.jewelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class registeruser extends AppCompatActivity {
    database helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);
        helper=new database(this);
    }
}