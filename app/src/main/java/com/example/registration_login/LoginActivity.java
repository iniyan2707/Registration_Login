package com.example.registration_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginPassword;
    private Button bttnLogin;
    private TextView loginTxt;
    private ProgressBar loginProgessBar;
    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        setUpUIViews();
        fAuth=FirebaseAuth.getInstance();

        FirebaseUser user= fAuth.getCurrentUser();


        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });
        bttnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    final String user_email=loginEmail.getText().toString().trim();
                    String user_password=loginPassword.getText().toString().trim();
                    loginProgessBar.setVisibility(View.VISIBLE);
                    fAuth.signInWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(LoginActivity.this,UserPage.class);
                                loginProgessBar.setVisibility(View.INVISIBLE);
                                i.putExtra("email",user_email);
                                finish();
                                startActivity(i);

                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Error ! :"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                loginProgessBar.setVisibility(View.GONE);


                            }

                        }
                    });
                    
                }
            }
        });
    }

    private void setUpUIViews()
    {
        loginEmail= (EditText) findViewById(R.id.EmailLogin);
        loginPassword= (EditText) findViewById(R.id.PasswordLogin);
        bttnLogin =(Button) findViewById(R.id.btnLogin);
        loginTxt = (TextView) findViewById(R.id.LoginTxt);
        loginProgessBar =(ProgressBar) findViewById(R.id.progressBar);
    }
    private Boolean validate()
    {
        Boolean result=false;

        String email=loginEmail.getText().toString();
        String password=loginPassword.getText().toString();

        if(email.isEmpty() || password.isEmpty() )
        {
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else if(password.length()< 6)
        {
            loginPassword.setError("Password must be >= 6 characters");
        }
        else
        {
            result=true;
        }
        return result;

    }
}