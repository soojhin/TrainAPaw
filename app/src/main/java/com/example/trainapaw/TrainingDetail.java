package com.example.trainapaw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TrainingDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_detail);

        Button btnTDA = findViewById(R.id.btnTDA);
        // Get the training method passed as an extra
        String trainingMethod = getIntent().getStringExtra("training_method");

        btnTDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainingDetail.this, TrainingDetail2.class);
                startActivity(intent);
            }
        });

        // Display the training method in the detail activity
        TextView detailTextView = findViewById(R.id.detailTextView);
        detailTextView.setText(trainingMethod);


    }
}
