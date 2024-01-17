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

public class intro8 extends AppCompatActivity {

    Button NextBtnIntro8;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro8);

        NextBtnIntro8 = findViewById(R.id.btnNextIntro8);

        RadioButton rbYesBasicCommands = findViewById(R.id.rbYesBasicCommands);

        radioGroup = findViewById(R.id.radioGroup);

        NextBtnIntro8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1){
                    SharedPreferences sharedPreferences = getSharedPreferences("dog_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("TEACH_BASIC_COMMANDS", false);
                    editor.apply();

                    if (rbYesBasicCommands.isChecked()) {

                        Intent intent = new Intent(intro8.this, termsconditions.class);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(intro8.this, ChooseBasicCommands.class);
                        startActivity(intent);
                    }
                }else {
                    // No radio button is checked, show a message
                    Toast.makeText(intro8.this, "Please choose Yes or No", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}