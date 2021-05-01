package com.example.registration_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class UserPage extends AppCompatActivity {

    private Button logoutBtn;
    private ImageView imageView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        setTitle("Info");

        logoutBtn =(Button) findViewById(R.id.logout);
        imageView =(ImageView) findViewById(R.id.imageView);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);

         //Uri uri = Uri.parse("https://scient.nitt.edu/images/scient-logo.png");

        Glide.with(this)
             .load(R.drawable.scient)
             .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
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
        final String fromEmail="iniyan2099@gmail.com";
        final String fromPassword="zootopia12";
        Bundle extras=getIntent().getExtras();
        String toEmail=extras.getString("email");
        String subject="Scient";
        String messageToSend="You have clicked scient image";
        new SendmailTask(UserPage.this,progressBar).execute(fromEmail,
                fromPassword, toEmail, subject, messageToSend);



    }


}