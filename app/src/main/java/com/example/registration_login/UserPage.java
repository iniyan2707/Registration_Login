package com.example.registration_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class UserPage extends AppCompatActivity {

    private Button logoutBtn;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        logoutBtn =(Button) findViewById(R.id.logout);
        imageView =(ImageView) findViewById(R.id.imageView);
         //Uri uri = Uri.parse("https://scient.nitt.edu/images/scient-logo.png");

        Glide.with(this)
             .load(R.drawable.scient)
             .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();


            }
        });



        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(UserPage.this,LoginActivity.class));
                finish();

            }
        });
    }

    private void sendEmail()
    {

    }


}