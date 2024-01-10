package com.example.jewelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewelapp.databinding.ActivityLoginPageBinding;

public class LoginPage extends AppCompatActivity {
    ActivityLoginPageBinding binding;
    DatabaseHelper databaseHelper;
    helper helper;
    helperclass helperclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        helper=new helper(this);
        helperclass=new helperclass(this);
        SharedPreferences sharedPreferences=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

//        databaseHelper = new DatabaseHelper(this);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();

                if (email.equals("")||password.equals("")){
                    Toast.makeText(LoginPage.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }else{
                    Cursor res = helperclass.login(email,password);
                    if (res.getCount() > 0) {

                        while (res.moveToNext()) {
                            int columnIndex1 = res.getColumnIndex("name");
                            int columnIndex2 = res.getColumnIndex("email");
                            int columnIndex3 = res.getColumnIndex("image");
                            if (columnIndex1 >= 0 && columnIndex2 >= 0 && columnIndex3 >= 0) {
                                String name = res.getString(columnIndex1);
                                String email1= res.getString(columnIndex2);
                                byte[] imageBytes = res.getBlob(columnIndex3);
                                String image = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                                editor.putString("name",name);
                                editor.putString("email",email1);
                                editor.putString("image",image);
                                editor.apply();

                            } else {
                                // handle the case where any of the column names are not found
                            }
                        }

                    } else {
                        Toast.makeText(LoginPage.this, "No record found", Toast.LENGTH_SHORT).show();
                    }

                    if (res.getCount()>0){
                        Toast.makeText(LoginPage.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),dashboard.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginPage.this, "invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),signup.class);
                startActivity(intent);
            }
        });


    }
}