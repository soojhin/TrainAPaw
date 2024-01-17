package com.example.trainapaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash3);

        Handler handler =new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                startActivity (new Intent( splash3.this, dashboard.class));
                finish ();
            }
        }, 1500);
    }
}