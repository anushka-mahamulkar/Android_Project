package com.example.jewelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class living_room extends AppCompatActivity {

    ImageView sofa1, recliner_1, tables_1, shoe_cabinet1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);

        sofa1 = findViewById(R.id.sofa1);
        recliner_1 = findViewById(R.id.recliner_1);
        tables_1 = findViewById(R.id.tables_1);
        shoe_cabinet1 = findViewById(R.id.shoe_cabinet1);

        sofa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}