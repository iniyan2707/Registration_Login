package com.example.registration_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText userName,userEmail,userPassword,userConfirmPassword;
    private Button btnRegister;
    private TextView regText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUiViews();
        firebaseAuth= FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                   String user_email= userEmail.getText().toString().trim();
                   String user_password=userPassword.getText().toString().trim();
                   firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                           if(task.isSuccessful())
                           {
                               Toast.makeText(MainActivity.this,"Registration Successfull",Toast.LENGTH_SHORT).show();
                               Intent i=new Intent(MainActivity.this,LoginActivity.class);
                               startActivity(i);
                           }
                           else
                           {
                               Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();

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
}