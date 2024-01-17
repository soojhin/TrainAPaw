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

public class ChooseSocializationTraining extends AppCompatActivity {

    Button NextBtn;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_socialization_training);

        NextBtn = findViewById(R.id.btnNext);

        RadioButton rbYesClicker = findViewById(R.id.rbYesSocialization);

        radioGroup = findViewById(R.id.radioGroup);

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1){
                    SharedPreferences sharedPreferences = getSharedPreferences("dog_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("TEACH_SOCIALIZATION_TRAINING", false);
                    editor.apply();

                    if (rbYesClicker.isChecked()) {
                        editor.putBoolean("TEACH_SOCIALIZATION_TRAINING", true);
                        editor.apply();
                        Toast.makeText(ChooseSocializationTraining.this, "We recommend socialization training!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChooseSocializationTraining.this, intro7.class);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(ChooseSocializationTraining.this, intro7.class);
                        startActivity(intent);
                    }
                }else {
                    // No radio button is checked, show a message
                    Toast.makeText(ChooseSocializationTraining.this, "Please choose Yes or No", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}