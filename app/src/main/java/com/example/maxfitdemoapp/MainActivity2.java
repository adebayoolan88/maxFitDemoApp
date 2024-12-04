package com.example.maxfitdemoapp;

import com.example.maxfitdemoapp.model.Person;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private EditText userNameEditText;
    private EditText userAgeEditText;
    private EditText userHeightEditText;
    private EditText userWeightEditText;
    private EditText userGenderEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        userNameEditText = findViewById(R.id.UserName);
        userAgeEditText = findViewById(R.id.UserAge);
        userHeightEditText = findViewById(R.id.UserHeight);
        userWeightEditText = findViewById(R.id.UserWeight);
        userGenderEditText = findViewById(R.id.UserGender);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button personalDetailsSubmit = findViewById(R.id.PersonalDetailsSubmit);
        personalDetailsSubmit.setOnClickListener(v -> {
            String name = userNameEditText.getText().toString().trim();
            String ageStr = userAgeEditText.getText().toString().trim();
            String heightStr = userHeightEditText.getText().toString().trim();
            String weightStr = userWeightEditText.getText().toString().trim();
            String gender = userGenderEditText.getText().toString().trim();

            if (name.isEmpty() || ageStr.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty() || gender.isEmpty()) {
                Toast.makeText(MainActivity2.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int age;
            double height;
            double weight;
            try {
                age = Integer.parseInt(ageStr);
                height = Double.parseDouble(heightStr);
                weight = Double.parseDouble(weightStr);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity2.this, "Please enter valid numbers for age, height, and weight", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a new Person object
            Person person = new Person(name, age, height, weight, gender);

            // Save the person data
            person.savePerson(this);

            // Navigate to the next activity
            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            startActivity(intent);
        });
    }
}

