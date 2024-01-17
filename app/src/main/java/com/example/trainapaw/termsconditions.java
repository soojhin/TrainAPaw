package com.example.trainapaw;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class termsconditions extends AppCompatActivity {

    Button NextBtn;

    CheckBox cb_agreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsconditions);

        cb_agreement = findViewById(R.id.cb_agreement);
        NextBtn = findViewById(R.id.btnNext);

        String message = "I agree to TrainaPaw Mobile Application's Terms and Conditions";
        SpannableString spannableString = new SpannableString(message);

        cb_agreement.setMovementMethod(LinkMovementMethod.getInstance());

        cb_agreement.setText(spannableString);

        cb_agreement.setMovementMethod(LinkMovementMethod.getInstance());

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_agreement.isChecked()) {
                    Intent intent = new Intent(termsconditions.this, AlarmReminder.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(termsconditions.this, "Please agree to the terms and conditions", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}