package com.example.registration_login;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;



  public class AlarmReceiver extends BroadcastReceiver{
      private Context context;
      @Override
      public void onReceive(Context context, Intent intent) {
          this.context=context;
          Toast.makeText(context,"Email sent to your registered email id",Toast.LENGTH_SHORT).show();
          sendEmail();
      }
//public class AlarmReceiver extends JobService {

   /* @Override
    public boolean onStartJob(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                sendEmail();

            }
        }).start();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }*/



    private void sendEmail()
    {
        final String fromEmail="iniyan2099@gmail.com";
        final String fromPassword="zootopia12";
        String subject="Scient";
        String messageToSend="You have clicked scient image";
        SharedPreferences sharedPreferences=context.getSharedPreferences(LoginActivity.SHARED_PREFS,MODE_PRIVATE);
        String toEmail= sharedPreferences.getString(LoginActivity.Login_Email,"");

        SendmailTask sendmailTask=new SendmailTask();

        sendmailTask.execute(fromEmail,fromPassword,toEmail, subject, messageToSend);
    }


}


