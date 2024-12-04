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

// Import Person and GoalLift classes
import com.example.maxfitdemoapp.model.Person;
import com.example.maxfitdemoapp.model.GoalLift;

public class MainActivity7 extends AppCompatActivity {

    private Person person;

    private EditText updateBenchEditText;
    private EditText updateSquatEditText;
    private EditText updateDeadliftEditText;
    private EditText updatePullupsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        person = Person.loadPerson(this);
        if (person == null) {
            Toast.makeText(this, "Error loading user data", Toast.LENGTH_SHORT).show();
            return;
        }

        updateBenchEditText = findViewById(R.id.UpdateBench);
        updateSquatEditText = findViewById(R.id.UpdateSquat);
        updateDeadliftEditText = findViewById(R.id.UpdateDeadlift);
        updatePullupsEditText = findViewById(R.id.UpdatePullups);

        Button updateGoalsSubmit = findViewById(R.id.UpdateGoalsSumbit);
        if (updateGoalsSubmit != null) {
            updateGoalsSubmit.setOnClickListener(v -> {
                updateGoalMaxes();

                person.savePerson(this);

                Toast.makeText(this, "Goals updated successfully", Toast.LENGTH_SHORT).show();

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
                Intent intent = new Intent(MainActivity7.this, MainActivity5.class);
                startActivity(intent);
            });
        }
    }

    /**
     * Updates the goalMax for each GoalLift based on user inputs.
     */
    private void updateGoalMaxes() {
        String updateBenchStr = updateBenchEditText.getText().toString().trim();
        String updateSquatStr = updateSquatEditText.getText().toString().trim();
        String updateDeadliftStr = updateDeadliftEditText.getText().toString().trim();
        String updatePullupsStr = updatePullupsEditText.getText().toString().trim();

        if (!updateBenchStr.isEmpty()) {
            updateGoalLiftMax("Bench Press", updateBenchStr);
        }
        if (!updateSquatStr.isEmpty()) {
            updateGoalLiftMax("Squat", updateSquatStr);
        }
        if (!updateDeadliftStr.isEmpty()) {
            updateGoalLiftMax("Deadlift", updateDeadliftStr);
        }
        if (!updatePullupsStr.isEmpty()) {
            updateGoalLiftMax("Pull-ups", updatePullupsStr);
        }
    }

    /**
     * Updates the goalMax of a specific GoalLift.
     *
     * @param liftName   The name of the lift.
     * @param goalMaxStr The new goal max as a string.
     */
    private void updateGoalLiftMax(String liftName, String goalMaxStr) {
        try {
            double newGoalMax = Double.parseDouble(goalMaxStr);

            GoalLift goalLift = findGoalLiftByName(liftName);

            if (goalLift != null) {
                goalLift.setGoalMax(newGoalMax);
            } else {
                Toast.makeText(this, "GoalLift for " + liftName + " not found.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number for " + liftName, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Finds a GoalLift by its name.
     *
     * @param liftName The name of the lift.
     * @return The GoalLift object, or null if not found.
     */
    private GoalLift findGoalLiftByName(String liftName) {
        for (GoalLift lift : person.getGoalLifts()) {
            if (lift.getName().equalsIgnoreCase(liftName)) {
                return lift;
            }
        }
        return null;
    }
}

