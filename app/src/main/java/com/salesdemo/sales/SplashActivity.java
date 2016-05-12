package com.salesdemo.sales;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

        try {
            //Init and Create database object
            DataBaseHelper mDBHelper = new DataBaseHelper(SplashActivity.this);
        } catch (Exception e){

        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
                overridePendingTransition(0, 0);

            }
        }, 1000);

    }
}
