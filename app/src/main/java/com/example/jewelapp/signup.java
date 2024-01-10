package com.example.jewelapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewelapp.databinding.ActivitySignupBinding;

import java.io.IOException;

public class signup extends AppCompatActivity {

    ActivitySignupBinding binding;
//    DatabaseHelper databaseHelper;
    helper h1;
    helperclass helperclass;
    EditText name,email,password;
    ImageView image;
    private Bitmap bitmapimage;
    private Uri uri;
    Button signup, login;
    boolean res;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        h1=new helper(this);
        helperclass=new helperclass(this);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        image=findViewById(R.id.imageview);
        password=findViewById(R.id.Password);
        signup=findViewById(R.id.signbutton);
        login=findViewById(R.id.loginbutton);



        ActivityResultLauncher<Intent> activityResultLauncher= registerForActivityResult
                (new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()== Activity.RESULT_OK){
                            Intent data=result.getData();
                            assert data !=null;
                            uri=data.getData();


                            try{
                                bitmapimage= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

                            }catch (IOException e){
                                Toast.makeText(signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            image.setImageBitmap(bitmapimage);
                        }else{
                            Toast.makeText(signup.this, "No image selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    activityResultLauncher.launch(intent);
                }catch (Exception e){
                    Toast.makeText(signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image.setDrawingCacheEnabled(true);
                image.buildDrawingCache();
                Bitmap bitmapimage = Bitmap.createBitmap(image.getDrawingCache());
                image.setDrawingCacheEnabled(false);

                    res=helperclass.insertUser(new registerclass(name.getText().toString(),email.getText().toString(),password.getText().toString(),bitmapimage));



                if(res==true){
                    Intent intent=new Intent(signup.this,LoginPage.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(signup.this, "Registeration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
            }
        });



    }
}