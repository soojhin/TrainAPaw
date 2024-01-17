package com.example.trainapaw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseClickerTraining extends AppCompatActivity {

    Button NextBtn;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_clicker_training);

        NextBtn = findViewById(R.id.btnNext);

        RadioButton rbYesClicker = findViewById(R.id.rbYesClicker);

        radioGroup = findViewById(R.id.radioGroup);

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1){
                    SharedPreferences sharedPreferences = getSharedPreferences("dog_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("TEACH_CLICKER_TRAINING", false);
                    editor.apply();

                    if (rbYesClicker.isChecked()) {
                        editor.putBoolean("TEACH_CLICKER_TRAINING", true);
                        editor.apply();
                        Toast.makeText(ChooseClickerTraining.this, "We recommend clicker training!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChooseClickerTraining.this, intro5.class);
                        startActivity(intent);

                    } else {

                        Intent intent = new Intent(ChooseClickerTraining.this, intro5.class);
                        startActivity(intent);
                    }
                }else {
                    // No radio button is checked, show a message
                    Toast.makeText(ChooseClickerTraining.this, "Please choose Yes or No", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Make the word "clicker" clickable and show description
        TextView clickerQuestion1 = findViewById(R.id.clickerQuestion1);
        makeWordClickable(clickerQuestion1);
    }

    private void makeWordClickable(TextView textView) {
        String fullText = textView.getText().toString();
        String clickableWord = "clicker";

        SpannableString spannableString = new SpannableString(fullText);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showClickerDescription(widget);
            }
        };

        int startIndex = fullText.indexOf(clickableWord);
        int endIndex = startIndex + clickableWord.length();

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void showClickerDescription(View view) {
        // Handle the click action for the clickable word "clicker"
        // For example, you can display a dialog with the description
        Intent intent = new Intent(this, ClickerDescriptionActivity.class);
        startActivity(intent);
    }
}
