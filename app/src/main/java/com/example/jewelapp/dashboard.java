package com.example.jewelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    SharedPreferences sharedPreferences;
    ImageView living_room1,dining_room1,decor1,kitchen1,furnishing1,bed_room_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textView = findViewById(R.id.textView);
        toolbar = findViewById(R.id.toolbar);
        living_room1 = findViewById(R.id.living_room1);
        dining_room1 = findViewById(R.id.dining_room1);
        decor1 = findViewById(R.id.decor1);
        kitchen1 = findViewById(R.id.kitchen1);
        furnishing1 = findViewById(R.id.furnishing1);
        bed_room_1 = findViewById(R.id.bed_room_1);

        setSupportActionBar(toolbar);

         sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        View header=navigationView.getHeaderView(0);


        String name=sharedPreferences.getString("name","");
        String email=sharedPreferences.getString("email","");
        String image=sharedPreferences.getString("image","");

        ImageView navimage=(ImageView) header.findViewById(R.id.profilepic);
        TextView navname=(TextView) header.findViewById(R.id.name);
        TextView navemail=(TextView) header.findViewById(R.id.email);
        navname.setText(name);
        navemail.setText(email);
        if (!image.isEmpty()) {
            byte[] imagebyte = Base64.decode(image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagebyte, 0, imagebyte.length);
            navimage.setImageBitmap(bitmap);
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        navigationView.bringToFront();

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        living_room1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),living_room.class);
                startActivity(intent);
            }
        });


        dining_room1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),dining_room.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem)
    {
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.location:
                Toast.makeText(this, "Visit our nearest station", Toast.LENGTH_SHORT).show();
                break;

            case R.id.about_us:
                Toast.makeText(this, "about us", Toast.LENGTH_SHORT).show();
                break;

            case R.id.contact_us:
                Intent i=new Intent(dashboard.this,gmail.class);
                startActivity(i);
                Toast.makeText(this, "contact us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("islogin","yes");
                editor.clear(); // Clear all stored data
                editor.apply();

//                        editor.remove("name");
//                        editor.remove("email");
//                        editor.apply();

//                        navname.setText("name");
//                        navemail.setText("email");
//                        navimage.setImageDrawable(null);
                Intent int1=new Intent(dashboard.this,LoginPage.class);
                startActivity(int1);
                finish();
                break;

            case R.id.bluetooth:
               Intent intent=new Intent(dashboard.this,bluetooth.class);
               startActivity(intent);

            case R.id.show:
                Intent show =new Intent(dashboard.this,show.class);
                startActivity(show);

            case R.id.update:
                Intent update=new Intent(dashboard.this,update.class);
                startActivity(update);

            case R.id.search:
                Intent search=new Intent(dashboard.this,search.class);
                startActivity(search);

        }
        return super.onOptionsItemSelected(item);
    }






       /* decor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        kitchen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        furnishing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bed_room_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


    }
