package com.example.jewelapp;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class registerclass {
    String name,email,password;
    Bitmap image;

    public registerclass(String name, String email, String password, Bitmap image) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
