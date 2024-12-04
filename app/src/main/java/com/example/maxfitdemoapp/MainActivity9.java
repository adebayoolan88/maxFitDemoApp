package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
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

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Navigate to Home Page
        ImageView homePageNav = findViewById(R.id.HomePageNavXML9);
        if (homePageNav != null) {
            homePageNav.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity9.this, MainActivity4.class);
                startActivity(intent);
            });
        }

        // Navigate to Add Workout Page
        ImageView addWorkoutPage = findViewById(R.id.AddWorkoutXML9);
        if (addWorkoutPage != null) {
            addWorkoutPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity9.this, MainActivity10.class);
                startActivity(intent);
            });
        }

        // Navigate to Recent Workout Page
        ImageView recentWorkoutPage = findViewById(R.id.recentWorkoutPageXML9);
        if (recentWorkoutPage != null) {
            recentWorkoutPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity9.this, MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigate to Goals Page
        ImageView goalsPage = findViewById(R.id.goalsPageNavXML9);
        if (goalsPage != null) {
            goalsPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity9.this, MainActivity7.class);
                startActivity(intent);
            });
        }

        // Navigate to Progress Page
        ImageView progressPage = findViewById(R.id.progressPageNavXML9);
        if (progressPage != null) {
            progressPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity9.this, MainActivity8.class);
                startActivity(intent);
            });
        }
    }
}

