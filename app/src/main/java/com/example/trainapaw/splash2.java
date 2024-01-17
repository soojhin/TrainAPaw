package com.example.trainapaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        Handler handler =new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                startActivity (new Intent( splash2.this, splash3.class));
                finish ();
            }
        }, 1500);
    }
}