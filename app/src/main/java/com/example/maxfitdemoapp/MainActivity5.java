package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Navigate to Recent Workout Page
        ImageView recentWorkoutPage = findViewById(R.id.RecentWorkoutPage);
        if (recentWorkoutPage != null) {
            recentWorkoutPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigate to Progress Page
        ImageView navigateToProgressPage = findViewById(R.id.NavigatetoProgressPage);
        if (navigateToProgressPage != null) {
            navigateToProgressPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity5.this, MainActivity8.class); // Replace with correct Progress Page activity
                startActivity(intent);
            });
        }

        // Navigate to Add Workout Page
        ImageView navigateToAddWorkoutPage = findViewById(R.id.addWorkoutIcon);
        if (navigateToAddWorkoutPage != null) {
            navigateToAddWorkoutPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity5.this, MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigate to Goals Page
        ImageView navigateToGoalsPage = findViewById(R.id.goalsIcon);
        if (navigateToGoalsPage != null) {
            navigateToGoalsPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity5.this, MainActivity7.class);
                startActivity(intent);
            });
        }
    }
}

