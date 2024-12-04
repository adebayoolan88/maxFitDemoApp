package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maxfitdemoapp.model.Person;
import com.example.maxfitdemoapp.model.Workout;

import java.util.List;

public class MainActivity6 extends AppCompatActivity {

    private int[] buttonIds = {
            R.id.previousDate1Button,
            R.id.previousDate2Button,
            R.id.previousDate3Button,
            R.id.previousDate4Button,
            R.id.previousDate5Button
    };

    private Button[] workoutButtons = new Button[5];

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        for (int i = 0; i < buttonIds.length; i++) {
            workoutButtons[i] = findViewById(buttonIds[i]);
            workoutButtons[i].setVisibility(View.GONE);
        }

        person = Person.loadPerson(this);
        if (person == null) {
            Toast.makeText(this, "Failed to load user data.", Toast.LENGTH_SHORT).show();
            return;
        }

        populateWorkoutButtons();

        setupNavigationIcons();
    }

    /**
     * Populates the workout buttons with the last 5 workout dates.
     */
    private void populateWorkoutButtons() {
        List<Workout> workoutHistory = person.getWorkoutHistory();
        int totalWorkouts = workoutHistory.size();
        int maxToDisplay = 5;
        int startIndex = Math.max(totalWorkouts - maxToDisplay, 0);
        int displayCount = totalWorkouts - startIndex;

        for (int i = 0; i < displayCount; i++) {
            final Workout workout = workoutHistory.get(startIndex + i);
            Button workoutButton = workoutButtons[i];
            workoutButton.setVisibility(View.VISIBLE);
            workoutButton.setText(workout.getDate());

            workoutButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity6.this, MainActivity8.class);
                intent.putExtra("selectedWorkout", workout);
                startActivity(intent);
            });
        }

        for (int i = displayCount; i < workoutButtons.length; i++) {
            workoutButtons[i].setVisibility(View.GONE);
        }
    }

    /**
     * Sets up the navigation icons with their respective OnClickListeners.
     */
    private void setupNavigationIcons() {
        // Navigate to Goals Page
        ImageView goalUpdateTab = findViewById(R.id.GoalUpdateTab);
        if (goalUpdateTab != null) {
            goalUpdateTab.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity6.this, MainActivity7.class);
                startActivity(intent);
            });
        }


        // Navigate to Add Workout Page
        ImageView addWorkoutTab = findViewById(R.id.AddWorkoutTab);
        if (addWorkoutTab != null) {
            addWorkoutTab.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity6.this, MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigate to Progress Page
        ImageView progressTab = findViewById(R.id.ProgressTab);
        if (progressTab != null) {
            progressTab.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity6.this, MainActivity5.class);
                startActivity(intent);
            });
        }
    }
}
