package com.example.registration_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {

    private EditText userName,userEmail,userPassword,userConfirmPassword;
    private Button btnRegister;
    private TextView regText;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Registration");
        setUpUiViews();
        firebaseAuth= FirebaseAuth.getInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                   String user_email= userEmail.getText().toString().trim();
                   String user_password=userPassword.getText().toString().trim();
                   progressBar.setVisibility(View.VISIBLE);
                   firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                           if(task.isSuccessful())
                           {
                               Toast.makeText(MainActivity.this,"Thank you for registration",Toast.LENGTH_SHORT).show();

                               Intent i=new Intent(MainActivity.this,LoginActivity.class);
                               finish();
                               startActivity(i);
                               sendEmail();


                           }
                           else
                           {
                               Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                               progressBar.setVisibility(View.GONE);

                           }
                       }
                   });
                }
            }
        });


        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void setUpUiViews()
    {
        userName= (EditText) findViewById(R.id.Username);
        userEmail= (EditText) findViewById(R.id.Email);
        userPassword= (EditText) findViewById(R.id.Password);
        userConfirmPassword=(EditText) findViewById(R.id.ConfirmPassword);
        btnRegister= (Button) findViewById(R.id.btnRegister);
        regText =(TextView) findViewById(R.id.RegTxt);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);

    }
    private Boolean validate()
    {
        Boolean result=false;
        String name=userName.getText().toString();
        String email=userEmail.getText().toString();
        String password=userPassword.getText().toString();
        String confirmPassword=userConfirmPassword.getText().toString();
        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
        {
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else if(password.length()< 6)
        {
            userPassword.setError("Password must be >= 6 characters");
        }
        else if(!confirmPassword.equals(password))
        {
            Toast.makeText(this,"Your password and confirm password doesn't match",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return result;

    }
    private void sendEmail()
    {
        final String fromEmail="iniyan2099@gmail.com";
        final String fromPassword="zootopia12";

        String toEmail=userEmail.getText().toString();
        String subject="Scient Registration";
        String messageToSend="You have successfully registered with scient";
        new SendmailTask(MainActivity.this,progressBar).execute(fromEmail,
                fromPassword, toEmail, subject, messageToSend);



    }
}