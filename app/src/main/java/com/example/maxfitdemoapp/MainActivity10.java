package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Navigate based on Yes or No selection
        TextView yesTextView = findViewById(R.id.yesTextView);
        if (yesTextView != null) {
            yesTextView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity10.this, MainActivity11.class);
                startActivity(intent);
            });
        }

        TextView noTextView = findViewById(R.id.noTextView);
        if (noTextView != null) {
            noTextView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity10.this, MainActivity8.class);
                startActivity(intent);
            });
        }


        // Navigation: Recent Workouts
        ImageView recentWorkoutIcon = findViewById(R.id.recentWorkoutIcon);
        if (recentWorkoutIcon != null) {
            recentWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity10.this, MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigation: Add Workout
        ImageView addWorkoutIcon = findViewById(R.id.addWorkoutIcon);
        if (addWorkoutIcon != null) {
            addWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity10.this, MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigation: Goals
        ImageView goalsIcon = findViewById(R.id.goalsIcon);
        if (goalsIcon != null) {
            goalsIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity10.this, MainActivity7.class);
                startActivity(intent);
            });
        }

        // Navigation: Progress
        ImageView progressPageIcon = findViewById(R.id.progressPageIcon);
        if (progressPageIcon != null) {
            progressPageIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity10.this, MainActivity8.class);
                startActivity(intent);
            });
        }
    }
}

