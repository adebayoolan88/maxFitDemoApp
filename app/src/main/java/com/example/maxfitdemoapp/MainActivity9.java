package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Generate Workout Button
        Button generateWorkoutButton = findViewById(R.id.generateWorkoutButton);
        if (generateWorkoutButton != null) {
            generateWorkoutButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity9.this, MainActivity10.class);
                startActivity(intent);
            });
        }

        // Navigate to Progress Page
        ImageView progressIcon = findViewById(R.id.progressIcon);
        if (progressIcon != null) {
            progressIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity9.this, MainActivity5.class);
                startActivity(intent);
            });
        }

        // Navigate to Recent Workout Page
        ImageView recentWorkoutIcon = findViewById(R.id.recentWorkoutIcon);
        if (recentWorkoutIcon != null) {
            recentWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity9.this, MainActivity6.class);
                startActivity(intent);
            });
        }


        // Navigate to Goals Page
        ImageView goalsIcon = findViewById(R.id.goalsIcon);
        if (goalsIcon != null) {
            goalsIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity9.this, MainActivity7.class);
                startActivity(intent);
            });
        }
    }
}


