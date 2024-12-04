package com.example.maxfitdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maxfitdemoapp.model.Person;
import com.example.maxfitdemoapp.model.GoalLift;

public class MainActivity3 extends AppCompatActivity {

    // Declare EditText fields
    private EditText currentBenchEditText;
    private EditText goalBenchEditText;
    private EditText currentSquatEditText;
    private EditText goalSquatEditText;
    private EditText currentDeadliftEditText;
    private EditText goalDeadliftEditText;
    private EditText currentPullupEditText;
    private EditText goalPullUpEditText;
    private EditText currentCardioEditText;
    private EditText goalCardioEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize EditText fields
        currentBenchEditText = findViewById(R.id.CurrentBench);
        goalBenchEditText = findViewById(R.id.GoalBench);
        currentSquatEditText = findViewById(R.id.CurrentSquat);
        goalSquatEditText = findViewById(R.id.GoalSquat);
        currentDeadliftEditText = findViewById(R.id.CurrentDeadlift);
        goalDeadliftEditText = findViewById(R.id.GoalDeadlift);
        currentPullupEditText = findViewById(R.id.CurrentPullup);
        goalPullUpEditText = findViewById(R.id.GoalPullUp);
        currentCardioEditText = findViewById(R.id.CurrentCardio);
        goalCardioEditText = findViewById(R.id.GoalCardio);

        Button userInitialGoals = findViewById(R.id.UserInitialGoals);
        userInitialGoals.setOnClickListener(v -> {
            String currentBenchStr = currentBenchEditText.getText().toString().trim();
            String goalBenchStr = goalBenchEditText.getText().toString().trim();
            String currentSquatStr = currentSquatEditText.getText().toString().trim();
            String goalSquatStr = goalSquatEditText.getText().toString().trim();
            String currentDeadliftStr = currentDeadliftEditText.getText().toString().trim();
            String goalDeadliftStr = goalDeadliftEditText.getText().toString().trim();
            String currentPullupStr = currentPullupEditText.getText().toString().trim();
            String goalPullUpStr = goalPullUpEditText.getText().toString().trim();
            String currentCardioStr = currentCardioEditText.getText().toString().trim();
            String goalCardioStr = goalCardioEditText.getText().toString().trim();

            if (currentBenchStr.isEmpty() || goalBenchStr.isEmpty() ||
                    currentSquatStr.isEmpty() || goalSquatStr.isEmpty() ||
                    currentDeadliftStr.isEmpty() || goalDeadliftStr.isEmpty() ||
                    currentPullupStr.isEmpty() || goalPullUpStr.isEmpty() ||
                    currentCardioStr.isEmpty() || goalCardioStr.isEmpty()) {
                Toast.makeText(MainActivity3.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double currentBench, goalBench;
            double currentSquat, goalSquat;
            double currentDeadlift, goalDeadlift;
            double currentPullup, goalPullUp;
            double currentCardio, goalCardio;

            try {
                currentBench = Double.parseDouble(currentBenchStr);
                goalBench = Double.parseDouble(goalBenchStr);
                currentSquat = Double.parseDouble(currentSquatStr);
                goalSquat = Double.parseDouble(goalSquatStr);
                currentDeadlift = Double.parseDouble(currentDeadliftStr);
                goalDeadlift = Double.parseDouble(goalDeadliftStr);
                currentPullup = Double.parseDouble(currentPullupStr);
                goalPullUp = Double.parseDouble(goalPullUpStr);
                currentCardio = Double.parseDouble(currentCardioStr);
                goalCardio = Double.parseDouble(goalCardioStr);
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity3.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                return;
            }

            GoalLift benchLift = new GoalLift("Bench Press", currentBench, currentBench, goalBench);
            GoalLift squatLift = new GoalLift("Squat", currentSquat, currentSquat, goalSquat);
            GoalLift deadliftLift = new GoalLift("Deadlift", currentDeadlift, currentDeadlift, goalDeadlift);
            GoalLift pullupLift = new GoalLift("Pull-ups", currentPullup, currentPullup, goalPullUp);
            GoalLift cardioLift = new GoalLift("Cardio", currentCardio, currentCardio, goalCardio);

            if (!Person.checkPerson(MainActivity3.this)) {
                Toast.makeText(MainActivity3.this, "No person data found. Please enter personal details first.", Toast.LENGTH_SHORT).show();
                return;
            }

            Person person = Person.loadPerson(MainActivity3.this);
            if (person == null) {
                Toast.makeText(MainActivity3.this, "Error loading user data", Toast.LENGTH_SHORT).show();
                return;
            }

            person.addGoalLift(benchLift);
            person.addGoalLift(squatLift);
            person.addGoalLift(deadliftLift);
            person.addGoalLift(pullupLift);
            person.addGoalLift(cardioLift);

            person.savePerson(MainActivity3.this);

            Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
            startActivity(intent);
        });
    }
}

