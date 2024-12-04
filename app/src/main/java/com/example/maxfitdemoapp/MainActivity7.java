package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Update Goals Submit Button
        Button updateGoalsSubmit = findViewById(R.id.UpdateGoalsSumbit);
        if (updateGoalsSubmit != null) {
            updateGoalsSubmit.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity7.this, MainActivity8.class);
                startActivity(intent);
            });
        }

        // Navigate to Recent Workout Page
        ImageView recentWorkoutTab = findViewById(R.id.RecentWorkoutTab);
        if (recentWorkoutTab != null) {
            recentWorkoutTab.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity7.this, MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigate to Add Workout Page
        ImageView addWorkoutTab = findViewById(R.id.AddWorkoutTab);
        if (addWorkoutTab != null) {
            addWorkoutTab.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity7.this, MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigate to Progress Page
        ImageView progressTab = findViewById(R.id.ProgressTab);
        if (progressTab != null) {
            progressTab.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity7.this, MainActivity8.class);
                startActivity(intent);
            });
        }

        // Navigate to Goals Page
        ImageView goalsTab = findViewById(R.id.GoalUpdateTab);
        if (goalsTab != null) {
            goalsTab.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity7.this, MainActivity7.class);
                startActivity(intent);
            });
        }
    }
}
