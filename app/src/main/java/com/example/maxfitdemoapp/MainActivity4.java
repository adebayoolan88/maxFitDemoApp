package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView; // Import TextView

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Import your Person class
import com.example.maxfitdemoapp.model.Person;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Person person = Person.loadPerson(this);

        TextView greetingText = findViewById(R.id.greetingText);

        if (person != null) {
            String userName = person.getName();

            String greeting = "Hello, " + userName + ", Let's get 1% better today";
            greetingText.setText(greeting);
        } else {
            greetingText.setText("Hello, User, Let's get 1% better today");
        }

        ImageView navigatetoProgressPage = findViewById(R.id.NavigatetoProgressPage);
        navigatetoProgressPage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
            startActivity(intent);
        });

        ImageView navigateToRecentWorkoutPage = findViewById(R.id.recentWorkoutIcon);
        navigateToRecentWorkoutPage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
            startActivity(intent);
        });

        // Navigate to Add Workout Page
        ImageView navigateToAddWorkoutPage = findViewById(R.id.addWorkoutIcon);
        navigateToAddWorkoutPage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity9.class);
            startActivity(intent);
        });

        // Navigate to Goals Page
        ImageView navigateToGoalsPage = findViewById(R.id.goalsIcon);
        navigateToGoalsPage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity4.this, MainActivity7.class);
            startActivity(intent);
        });
    }
}

