package com.example.jewelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class show extends AppCompatActivity {
    StringBuffer stringBuffer;
    helperclass databasehelper;
    Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        stringBuffer = new StringBuffer();
        view = findViewById(R.id.show_btn);

        databasehelper=new helperclass(this);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an instance of helperclass
                helperclass dbHelper = new helperclass(show.this);

                // Call the viewRecords() method on the instance
                Cursor res = dbHelper.viewRecords();

                stringBuffer = new StringBuffer();
                while (res.moveToNext()) {
                    stringBuffer.append("name :" + res.getString(0));
                    stringBuffer.append("\n email: " + res.getString(1));
                    stringBuffer.append("\n password :" + res.getString(2));
                    stringBuffer.append("\n\n");
                }
            }
        });

    }
}