package com.example.registration_login;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class UserPage extends AppCompatActivity {

    private Button logoutBtn;
    private ImageView imageView;

      public String toEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        setTitle("Info");

        logoutBtn =(Button) findViewById(R.id.logout);
        imageView =(ImageView) findViewById(R.id.imageView);



         //Uri uri = Uri.parse("https://scient.nitt.edu/images/scient-logo.png");

        Glide.with(this)
             .load(R.drawable.scient)
             .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {


              //setJob();
               setAlarm();


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



    /* private void setJob() {

         ComponentName serviceComponent = new ComponentName(this, AlarmReceiver.class);

         JobInfo jobInfo = new JobInfo.Builder(1, serviceComponent)
                 .setRequiresDeviceIdle(false)
                 .setRequiresCharging(false)
                 .setPersisted(true)
                 .setMinimumLatency(300 * 1000)
                 .build();

         JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
         int res = scheduler.schedule(jobInfo);
         if (res == JobScheduler.RESULT_SUCCESS) {
             Toast.makeText(this, "Email Scheduled Success", Toast.LENGTH_SHORT).show();
         } else {
             Toast.makeText(this, "Email Scheduled Failed", Toast.LENGTH_SHORT).show();
         }


     }*/
    private void setAlarm()
    {
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,1,intent,0);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(5*60*1000),pendingIntent);
        Toast.makeText(this,"Email scheduled successfully",Toast.LENGTH_SHORT).show();
    }


}