package com.sys.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sys.MainActivity;
import com.sys.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }
    private void init(){
         timer = new Timer();
         timer.schedule(new TimerTask() {
             @Override
             public void run() {
                 toMain();
             }
         }, 500);
    }

    public void toMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void toLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }



}
