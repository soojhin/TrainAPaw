package com.example.trainapaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class intro7 extends AppCompatActivity {

    Button NextBtnIntro7;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro7);

        NextBtnIntro7 = findViewById(R.id.btnNextIntro7);

        RadioButton rbYesBasicCommands = findViewById(R.id.rbYesBasicCommands);

        radioGroup = findViewById(R.id.radioGroup);

        NextBtnIntro7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1){
                    SharedPreferences sharedPreferences = getSharedPreferences("dog_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("TEACH_BASIC_COMMANDS", false);
                    editor.apply();

                    if (rbYesBasicCommands.isChecked()) {

                        Intent intent = new Intent(intro7.this, termsconditions.class);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(intro7.this, intro8.class);
                        startActivity(intent);
                    }
                }else {
                    // No radio button is checked, show a message
                    Toast.makeText(intro7.this, "Please choose Yes or No", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}