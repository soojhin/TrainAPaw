package com.example.trainapaw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class intro2 extends AppCompatActivity {

    Button btnNextIntro2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);

        Spinner spinner = findViewById(R.id.editSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.breeds, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        EditText etdogName = findViewById(R.id.etdogName);
        EditText ageEditText = findViewById(R.id.etAge);

        btnNextIntro2 = findViewById(R.id.btnNextIntro2);

        btnNextIntro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dogName = etdogName.getText().toString();
                String ageText = ageEditText.getText().toString();

                if (!dogName.isEmpty() && !ageText.isEmpty()) {
                    String dogBreed = spinner.getSelectedItem().toString();
                    int dogAge = Integer.parseInt(ageText);

                    SharedPreferences sharedPreferences = getSharedPreferences("dog_info", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("NAME", dogName);
                    editor.putString("BREED", dogBreed);
                    editor.putInt("AGE", dogAge);
                    editor.apply();

                    Intent intent = new Intent(intro2.this, intro3.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(intro2.this, "Please enter the dog's name and age", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
