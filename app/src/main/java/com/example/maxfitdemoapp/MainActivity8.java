package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maxfitdemoapp.model.Exercise;
import com.example.maxfitdemoapp.model.Workout;

import java.util.List;

public class MainActivity8 extends AppCompatActivity {

    private TextView workoutHeaderText;
    private TextView workoutDateTextView;
    private LinearLayout exercisesLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        workoutHeaderText = findViewById(R.id.workoutHeaderText);
        exercisesLinearLayout = findViewById(R.id.exercisesLinearLayout);

        Workout selectedWorkout = (Workout) getIntent().getSerializableExtra("selectedWorkout");
        if (selectedWorkout != null) {
            String headerText = "Here is your workout for:\n" + selectedWorkout.getDate();
            workoutHeaderText.setText(headerText);

            addExercises(selectedWorkout.getExercises());
        } else {
            Toast.makeText(this, "No workout data received.", Toast.LENGTH_SHORT).show();
            workoutHeaderText.setText("No workout selected.");
        }

        // Setup navigation icons
        setupNavigationIcons();
    }

    /**
     * Adds exercises to the exercisesLinearLayout dynamically.
     *
     * @param exercises List of Exercise objects to display.
     */
    private void addExercises(List<Exercise> exercises) {
        exercisesLinearLayout.removeAllViews();
        for (Exercise exercise : exercises) {
            TextView exerciseTextView = new TextView(this);
            exerciseTextView.setText(exercise.toString());
            exerciseTextView.setBackgroundColor(getResources().getColor(R.color.black));
            exerciseTextView.setTextColor(getResources().getColor(R.color.textColor));
            exerciseTextView.setTextSize(18);
            exerciseTextView.setPadding(16, 16, 16, 16);
            exerciseTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, 16); // Bottom margin for spacing
            exerciseTextView.setLayoutParams(params);
            exercisesLinearLayout.addView(exerciseTextView);
        }
    }

    /**
     * Sets up the navigation icons with their respective OnClickListeners.
     */
    private void setupNavigationIcons() {
        // Navigate to Goals Page
        ImageView goalsIcon = findViewById(R.id.GoalUpdateTab);
        if (goalsIcon != null) {
            goalsIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity8.this, MainActivity7.class);
                startActivity(intent);
            });
        }

        // Navigate to Recent Workouts
        ImageView recentWorkoutIcon = findViewById(R.id.RecentWorkoutTab);
        if (recentWorkoutIcon != null) {
            recentWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity8.this, MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigate to Add Workout Page
        ImageView addWorkoutIcon = findViewById(R.id.AddWorkoutTab);
        if (addWorkoutIcon != null) {
            addWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity8.this, MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigate to Progress Page
        ImageView progressPageIcon = findViewById(R.id.ProgressTab);
        if (progressPageIcon != null) {
            progressPageIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity8.this, MainActivity5.class);
                startActivity(intent);
            });
        }

    }
}


