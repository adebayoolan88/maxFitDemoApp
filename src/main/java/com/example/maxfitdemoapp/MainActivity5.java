package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maxfitdemoapp.model.GoalLift;
import com.example.maxfitdemoapp.model.Person;

public class MainActivity5 extends AppCompatActivity {

    // UI Components
    private EditText newBenchMaxEditText;
    private EditText newSquatMaxEditText;
    private EditText newDeadliftMaxEditText;
    private EditText newPullupMaxEditText;
    private EditText newCardioMaxEditText;

    private TextView benchPercentChangeTextView;
    private TextView squatPercentChangeTextView;
    private TextView deadliftPercentChangeTextView;
    private TextView pullupPercentChangeTextView;
    private TextView cardioPercentChangeTextView;

    private Button calculatePercentChangeButton;

    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                });

        newBenchMaxEditText = findViewById(R.id.NewBenchMax);
        newSquatMaxEditText = findViewById(R.id.NewSquatMax);
        newDeadliftMaxEditText = findViewById(R.id.NewDeadliftMax);
        newPullupMaxEditText = findViewById(R.id.NewPullupMax);
        newCardioMaxEditText = findViewById(R.id.NewCardioMax);

        benchPercentChangeTextView = findViewById(
                R.id.BenchPercentChange);
        squatPercentChangeTextView = findViewById(
                R.id.SquatPercentChange);
        deadliftPercentChangeTextView = findViewById(
                R.id.DeadliftPercentChange);
        pullupPercentChangeTextView = findViewById(
                R.id.PullupPercentChange);
        cardioPercentChangeTextView = findViewById(
                R.id.CardioPercentChange);

        calculatePercentChangeButton = findViewById(
                R.id.calculatePercentChangeButton);

        person = Person.loadPerson(this);
        if (person == null) {
            Toast.makeText(this, "Error loading user data",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        calculatePercentChangeButton.setOnClickListener(v -> {
            // Get user inputs
            String newBenchMaxStr = newBenchMaxEditText.getText()
                    .toString().trim();
            String newSquatMaxStr = newSquatMaxEditText.getText()
                    .toString().trim();
            String newDeadliftMaxStr = newDeadliftMaxEditText.getText()
                    .toString().trim();
            String newPullupMaxStr = newPullupMaxEditText.getText()
                    .toString().trim();
            String newCardioMaxStr = newCardioMaxEditText.getText()
                    .toString().trim();

            if (!newBenchMaxStr.isEmpty()) {
                updateGoalLift("Bench Press", newBenchMaxStr,
                        benchPercentChangeTextView);
            }

            if (!newSquatMaxStr.isEmpty()) {
                updateGoalLift("Squat", newSquatMaxStr,
                        squatPercentChangeTextView);
            }

            if (!newDeadliftMaxStr.isEmpty()) {
                updateGoalLift("Deadlift", newDeadliftMaxStr,
                        deadliftPercentChangeTextView);
            }

            if (!newPullupMaxStr.isEmpty()) {
                updateGoalLift("Pull-ups", newPullupMaxStr,
                        pullupPercentChangeTextView);
            }

            if (!newCardioMaxStr.isEmpty()) {
                updateGoalLift("Cardio", newCardioMaxStr,
                        cardioPercentChangeTextView);
            }

            person.savePerson(this);
            Toast.makeText(this, "Percent changes calculated and "
                    + "data saved.", Toast.LENGTH_SHORT).show();
        });

        // Navigate to Recent Workout Page
        ImageView recentWorkoutPage = findViewById(
                R.id.RecentWorkoutTab);
        if (recentWorkoutPage != null) {
            recentWorkoutPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity5.this,
                        MainActivity6.class);
                startActivity(intent);
            });
        }

        // Navigate to Add Workout Page
        ImageView navigateToAddWorkoutPage = findViewById(
                R.id.AddWorkoutTab);
        if (navigateToAddWorkoutPage != null) {
            navigateToAddWorkoutPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity5.this,
                        MainActivity9.class);
                startActivity(intent);
            });
        }

        // Navigate to Goals Page
        ImageView navigateToGoalsPage = findViewById(R.id.GoalUpdateTab);
        if (navigateToGoalsPage != null) {
            navigateToGoalsPage.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity5.this,
                        MainActivity7.class);
                startActivity(intent);
            });
        }
    }

    /**
     * Updates the GoalLift with the new max, calculates percent change,
     * and updates the TextView.
     *
     * @param liftName          The name of the lift.
     * @param newMaxStr         The new max as a string.
     * @param percentChangeView The TextView to display percent change.
     */
    private void updateGoalLift(String liftName, String newMaxStr,
                                TextView percentChangeView) {
        try {
            double newMax = Double.parseDouble(newMaxStr);

            // Find the corresponding GoalLift
            GoalLift goalLift = findGoalLiftByName(liftName);

            if (goalLift != null) {
                // Update previousMax and currentMax
                goalLift.setPreviousMax(goalLift.getCurrentMax());
                goalLift.setCurrentMax(newMax);

                double percentChange = goalLift.calculatePercentChange();

                String percentChangeText = String.format(
                        "%.2f%%", percentChange);
                percentChangeView.setText(percentChangeText);
            } else {
                Toast.makeText(this, "GoalLift for " + liftName
                        + " not found.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number for "
                    + liftName, Toast.LENGTH_SHORT).show();
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


