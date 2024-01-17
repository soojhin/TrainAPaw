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

public class intro4 extends AppCompatActivity {

    Button NextBtnIntro4;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro4);

        NextBtnIntro4 = findViewById(R.id.btnNextIntro4);

        RadioButton rbYesClicker = findViewById(R.id.rbYesClicker);

        radioGroup = findViewById(R.id.radioGroup);

        NextBtnIntro4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {
                    SharedPreferences sharedPreferences = getSharedPreferences("dog_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("TEACH_CLICKER_TRAINING", false);
                    editor.apply();

                    if (rbYesClicker.isChecked()) {
                        // Proceed to the next activity
                        Intent intent = new Intent(intro4.this, ChooseClickerTraining.class);
                        startActivity(intent);
                    } else {
                        // Proceed to the next activity
                        Intent intent = new Intent(intro4.this, intro5.class);
                        startActivity(intent);
                    }
                }else {
                    // No radio button is checked, show a message
                    Toast.makeText(intro4.this, "Please choose Yes or No", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}