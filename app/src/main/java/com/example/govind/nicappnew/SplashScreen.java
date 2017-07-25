package com.example.govind.nicappnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;

/**
 * Created by govind on 17-Apr-17.
 */

public class SplashScreen extends Activity {

    private static int SPLASH_TIME_OUT=9000;
    protected void onCreate(Bundle savedInstanceState){
super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timerThread = new Thread(){
         public void run(){
                  try{
                   sleep(4000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                    }finally{
                      Intent intent = new Intent(SplashScreen.this,loginselect.class);
                     startActivity(intent);
                           }
                             }
                             };
                  timerThread.start();
                   }
           @Override
                  protected void onPause() {
// TODO Auto-generated method stub
                super.onPause();
          finish();


    }


}
