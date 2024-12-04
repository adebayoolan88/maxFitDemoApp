package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Submit Button
        Button submitButton = findViewById(R.id.submitButtonXML11);
        if (submitButton != null) {
            submitButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity11.this, MainActivity8.class);
                startActivity(intent);
            });
        }


        // Navigation: Recent Workouts
        ImageView recentWorkoutIcon = findViewById(R.id.recentWorkoutIconXML11);
        if (recentWorkoutIcon != null) {
            recentWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity11.this, MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigation: Add Workout
        ImageView addWorkoutIcon = findViewById(R.id.addWorkoutIconXML11);
        if (addWorkoutIcon != null) {
            addWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity11.this, MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigation: Goals
        ImageView goalsIcon = findViewById(R.id.goalsIconXML11);
        if (goalsIcon != null) {
            goalsIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity11.this, MainActivity7.class);
                startActivity(intent);
            });
        }

        // Navigation: Progress
        ImageView progressPageIcon = findViewById(R.id.progressPageIconXML11);
        if (progressPageIcon != null) {
            progressPageIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity11.this, MainActivity8.class);
                startActivity(intent);
            });
        }
    }
}
