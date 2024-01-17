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

public class intro3 extends AppCompatActivity {

    Button NextBtnIntro3;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro3);

        NextBtnIntro3 = findViewById(R.id.btnNextIntro3);

        RadioButton rbYesClicker = findViewById(R.id.rbYesClicker);

        radioGroup = findViewById(R.id.radioGroup);

        NextBtnIntro3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1){
                    SharedPreferences sharedPreferences = getSharedPreferences("dog_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("TEACH_CLICKER_TRAINING", false);
                    editor.apply();

                    if (rbYesClicker.isChecked()) {
                        Intent intent = new Intent(intro3.this, intro4.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(intro3.this, intro5.class);
                        startActivity(intent);
                    }
                }else {
                    // No radio button is checked, show a message
                    Toast.makeText(intro3.this, "Please choose Yes or No", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}