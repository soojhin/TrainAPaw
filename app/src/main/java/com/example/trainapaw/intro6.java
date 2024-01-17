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

public class intro6 extends AppCompatActivity {

    Button NextBtnIntro6;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro6);

        NextBtnIntro6 = findViewById(R.id.btnNextIntro6);

        RadioButton rbYesSocialization = findViewById(R.id.rbYesSocialization);

        radioGroup = findViewById(R.id.radioGroup);

        NextBtnIntro6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1){
                    SharedPreferences sharedPreferences = getSharedPreferences("dog_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("TEACH_SOCIALIZATION_TRAINING", false);
                    editor.apply();

                    if (rbYesSocialization.isChecked()) {

                        Intent intent = new Intent(intro6.this, intro7.class);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(intro6.this, ChooseSocializationTraining.class);
                        startActivity(intent);
                    }
                }else {
                    // No radio button is checked, show a message
                    Toast.makeText(intro6.this, "Please choose Yes or No", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}