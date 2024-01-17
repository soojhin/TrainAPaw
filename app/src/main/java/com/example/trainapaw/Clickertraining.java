package com.example.trainapaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Clickertraining extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickertraining);

        ImageView imageView = findViewById(R.id.clickerPicture);
        TextView tvExercise = findViewById(R.id.tvExercise);
        Button btnFinish = findViewById(R.id.btnFinish);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(Clickertraining.this, R.raw.clicksound);
                mediaPlayer.start();
            }
        });

        tvExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Clickertraining.this, TrainingDetail.class);
                startActivity(intent);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Clickertraining.this, dashboard.class);
                startActivity(intent);
            }
        });
    }
}
