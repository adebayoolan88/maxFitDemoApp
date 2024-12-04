package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maxfitdemoapp.model.Exercise;

public class MainActivity11 extends AppCompatActivity {

    private EditText exerciseNameInput, repsInput, setsInput;
    private Button addButton, returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        exerciseNameInput = findViewById(R.id.exerciseNameInput);
        repsInput = findViewById(R.id.repsInput);
        setsInput = findViewById(R.id.setsInput);

        addButton = findViewById(R.id.addButton);
        returnButton = findViewById(R.id.returnButton);

        addButton.setOnClickListener(v -> {
            String name = exerciseNameInput.getText().toString().trim();
            String repsStr = repsInput.getText().toString().trim();
            String setsStr = setsInput.getText().toString().trim();

            if (name.isEmpty() || repsStr.isEmpty() || setsStr.isEmpty()) {
                Toast.makeText(MainActivity11.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int reps = Integer.parseInt(repsStr);
                int sets = Integer.parseInt(setsStr);

                Exercise newExercise = new Exercise(name, reps, sets);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("newExercise", newExercise);

                setResult(RESULT_OK, resultIntent);
                finish();

            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity11.this, "Reps and Sets must be numbers.", Toast.LENGTH_SHORT).show();
            }
        });

        // Set OnClickListener for Return Without Adding Button
        returnButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        // Navigate to Recent Workouts
        ImageView recentWorkoutIcon = findViewById(R.id.recentWorkoutIcon);
        if (recentWorkoutIcon != null) {
            recentWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity11.this, MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigate to Add Workout
        ImageView addWorkoutIcon = findViewById(R.id.addWorkoutIcon);
        if (addWorkoutIcon != null) {
            addWorkoutIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity11.this, MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigate to Goals
        ImageView goalsIcon = findViewById(R.id.goalsIcon);
        if (goalsIcon != null) {
            goalsIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity11.this, MainActivity7.class);
                startActivity(intent);
            });
        }

        // Navigate to Progress
        ImageView progressPageIcon = findViewById(R.id.progressPageIcon);
        if (progressPageIcon != null) {
            progressPageIcon.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity11.this, MainActivity5.class);
                startActivity(intent);
            });
        }
    }
}

