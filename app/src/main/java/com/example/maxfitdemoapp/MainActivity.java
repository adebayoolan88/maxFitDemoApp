package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Import the Person class
import com.example.maxfitdemoapp.model.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button startButton = findViewById(R.id.AppStart);
        startButton.setOnClickListener(v -> {
            boolean personExists = Person.checkPerson(MainActivity.this);

            boolean goalLiftsExists = Person.checkGoalLifts(MainActivity.this);

            Intent intent;

            if (personExists && goalLiftsExists) {
                // Both files exist, navigate to MainActivity4
                intent = new Intent(MainActivity.this, MainActivity4.class);
            } else if (personExists) {
                // Only person.csv exists, navigate to MainActivity3
                intent = new Intent(MainActivity.this, MainActivity3.class);
            } else {
                // Neither file exists, navigate to MainActivity2
                intent = new Intent(MainActivity.this, MainActivity2.class);
            }

            startActivity(intent);
        });
    }
}
