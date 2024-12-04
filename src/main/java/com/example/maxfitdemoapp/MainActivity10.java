package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maxfitdemoapp.model.Exercise;
import com.example.maxfitdemoapp.model.Person;
import com.example.maxfitdemoapp.model.Workout;

public class MainActivity10 extends AppCompatActivity {

    private LinearLayout exercisesLinearLayout;
    private Button addExerciseButton, saveWorkoutButton;
    private Workout workout;
    private Person person;
    private ActivityResultLauncher<Intent> addExerciseLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        workout = new Workout();
        exercisesLinearLayout = findViewById(R.id.exercisesLinearLayout);

        workout.loadExercises(this);
        workout.generateRandomWorkout();

        displayWorkout();

        addExerciseButton = findViewById(R.id.addExerciseButton);
        saveWorkoutButton = findViewById(R.id.saveWorkoutButton);

        addExerciseLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Exercise newExercise = (Exercise) result.getData().getSerializableExtra("newExercise");
                        if (newExercise != null) {
                            workout.addExercise(newExercise);
                            addExerciseView(newExercise);
                            Toast.makeText(MainActivity10.this, "Exercise added.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity10.this, "No exercise added.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        addExerciseButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity10.this, MainActivity11.class);
            intent.putExtra("workout", workout);
            addExerciseLauncher.launch(intent);
        });

        saveWorkoutButton.setOnClickListener(v -> {
            saveWorkout();
        });

        // Navigate to Recent Workouts
        ImageView recentWorkoutIcon = findViewById(R.id.recentWorkoutIcon);
        if (recentWorkoutIcon != null) {
            recentWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity10.this, MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigate to Add Workout
        ImageView addWorkoutIcon = findViewById(R.id.addWorkoutIcon);
        if (addWorkoutIcon != null) {
            addWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity10.this, MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigate to Goals
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
                Intent intent = new Intent(MainActivity10.this, MainActivity5.class);
                startActivity(intent);
            });
        }
    }

    private void displayWorkout() {
        exercisesLinearLayout.removeAllViews();
        for (Exercise exercise : workout.getExercises()) {
            addExerciseView(exercise);
            Log.d("MainActivity10", "Added to screen: " + exercise.toString());
        }
    }

    private void addExerciseView(Exercise exercise) {
        TextView exerciseTextView = new TextView(this);
        exerciseTextView.setText(exercise.toString());
        exerciseTextView.setBackgroundColor(getResources().getColor(R.color.black));
        exerciseTextView.setTextColor(getResources().getColor(R.color.textColor));
        exerciseTextView.setTextSize(20);
        exerciseTextView.setPadding(16, 16, 16, 16);
        exerciseTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 16);
        exerciseTextView.setLayoutParams(params);
        exercisesLinearLayout.addView(exerciseTextView);
    }

    private void saveWorkout() {

        person = Person.loadPerson(this);

        if (person != null) {
            person.updateWorkoutHistory(workout);
            person.savePerson(this);
            Toast.makeText(MainActivity10.this, "Workout saved successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity10.this, "Error: User not found.", Toast.LENGTH_SHORT).show();
        }
    }
}



