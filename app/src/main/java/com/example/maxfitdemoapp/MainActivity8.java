package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Navigate to Add Workout Page
        ImageView addWorkoutPageNav = findViewById(R.id.addWorkoutPageNav);
        if (addWorkoutPageNav != null) {
            addWorkoutPageNav.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity8.this, MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigate to Home Page
        ImageView homePageNav = findViewById(R.id.HomePageNav);
        if (homePageNav != null) {
            homePageNav.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity8.this, MainActivity4.class);
                startActivity(intent);
            });
        }

        // Navigate to Recent Workouts
        ImageView recentWorkoutIcon = findViewById(R.id.recentWorkoutIcon);
        if (recentWorkoutIcon != null) {
            recentWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity8.this, MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigate to Goals Page
        ImageView goalsIcon = findViewById(R.id.GoalsPageIcon);
        if (goalsIcon != null) {
            goalsIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity8.this, MainActivity7.class);
                startActivity(intent);
            });
        }

        // Navigate to Progress Page
        ImageView progressPageIcon = findViewById(R.id.progressPageIcon);
        if (progressPageIcon != null) {
            progressPageIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity8.this, MainActivity8.class);
                startActivity(intent);
            });
        }
    }
}

