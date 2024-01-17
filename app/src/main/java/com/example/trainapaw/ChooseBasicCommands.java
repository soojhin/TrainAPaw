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

public class ChooseBasicCommands extends AppCompatActivity {

    Button NextBtn;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_basic_commands);

        NextBtn = findViewById(R.id.btnNext);

        RadioButton rbYesBasicCommands = findViewById(R.id.rbYesBasicCommands);

        radioGroup = findViewById(R.id.radioGroup);

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1){
                    SharedPreferences sharedPreferences = getSharedPreferences("dog_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("TEACH_BASIC_COMMANDS", false);
                    editor.apply();

                    if (rbYesBasicCommands.isChecked()) {
                        editor.putBoolean("TEACH_BASIC_COMMANDS", true);
                        editor.apply();
                        Toast.makeText(ChooseBasicCommands.this, "We recommend basic command training!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChooseBasicCommands.this, termsconditions.class);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(ChooseBasicCommands.this, termsconditions.class);
                        startActivity(intent);
                    }
                }else {
                    // No radio button is checked, show a message
                    Toast.makeText(ChooseBasicCommands.this, "Please choose Yes or No", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}