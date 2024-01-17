package com.example.trainapaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class TrainingDetail2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_detail2);
        Button btnToClicker = findViewById(R.id.btnToClicker);
        VideoView videoView = findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R. raw.clickervid;

        Uri uri = Uri.parse (videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController( this);
        videoView.setMediaController (mediaController);
        mediaController.setAnchorView (videoView);

        btnToClicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainingDetail2.this, Clickertraining.class);
                startActivity(intent);
            }
        });

    }
}