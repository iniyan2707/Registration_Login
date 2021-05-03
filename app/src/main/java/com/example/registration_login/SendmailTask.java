package com.example.registration_login;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextPaint;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SendmailTask extends AsyncTask{





    @Override
    protected Object doInBackground(Object[] args) {

        try{
            GMail androidEmail = new GMail(args[0].toString(),
                    args[1].toString(),  args[2].toString(), args[3].toString(),
                    args[4].toString());

            androidEmail.createEmailMessage();
            androidEmail.sendEmail();

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    protected void onPostExecute(Object o) {



    }
}
