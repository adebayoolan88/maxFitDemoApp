package com.example.maxfitdemoapp.model;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

public class Workout implements Serializable {

    private ArrayList<Exercise> currentExercises;
    private List<Exercise> chestExercises;
    private List<Exercise> shoulderExercises;
    private List<Exercise> backExercises;
    private List<Exercise> armExercises;
    private List<Exercise> absExercises;
    private List<Exercise> legExercises;

    private String date;

    // Constructor
    public Workout() {
        this.currentExercises = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        date = dateFormat.format(calendar.getTime());
    }

    public void loadExercises(Activity activity) {
        chestExercises = new ArrayList<>();
        shoulderExercises = new ArrayList<>();
        backExercises = new ArrayList<>();
        armExercises = new ArrayList<>();
        absExercises = new ArrayList<>();
        legExercises = new ArrayList<>();

        try {
            // Read the exercise data from a resource file
            AssetManager assetManager = activity.getAssets();
            InputStream exerciseInputStream = assetManager.open("workouts.csv");
            Scanner scanner = new Scanner(exerciseInputStream);
            scanner.nextLine(); // Skip header line

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] exerciseData = line.split(",");
                String category = exerciseData[0];
                String name = exerciseData[1];
                int reps = Integer.parseInt(exerciseData[2]);
                int sets = Integer.parseInt(exerciseData[3]);
                Exercise exercise = new Exercise(name, reps, sets);

                switch (category.toLowerCase()) {
                    case "chest":
                        chestExercises.add(exercise);
                        break;
                    case "shoulders":
                        shoulderExercises.add(exercise);
                        break;
                    case "back":
                        backExercises.add(exercise);
                        break;
                    case "arms":
                        armExercises.add(exercise);
                        break;
                    case "abs":
                        absExercises.add(exercise);
                        break;
                    case "legs":
                        legExercises.add(exercise);
                        break;
                }
            }
            scanner.close();
            Log.d("Workout", "Loaded " + chestExercises.size() + " chest exercises.");
        } catch (IOException e) {
            // Handle the IOException
            e.printStackTrace();
            Log.e("Workout", "youre cooked " + chestExercises.size() + " chest exercises.");
        }
    }


    // Generate a random workout by selecting one exercise from each category
    public void generateRandomWorkout() {
        Random random = new Random();
        if (!chestExercises.isEmpty()) {
            currentExercises.add(chestExercises.get(random.nextInt(chestExercises.size())));
        }
        if (!shoulderExercises.isEmpty()) {
            currentExercises.add(shoulderExercises.get(random.nextInt(shoulderExercises.size())));
        }
        if (!backExercises.isEmpty()) {
            currentExercises.add(backExercises.get(random.nextInt(backExercises.size())));
        }
        if (!armExercises.isEmpty()) {
            currentExercises.add(armExercises.get(random.nextInt(armExercises.size())));
        }
        if (!absExercises.isEmpty()) {
            currentExercises.add(absExercises.get(random.nextInt(absExercises.size())));
        }
        if (!legExercises.isEmpty()) {
            currentExercises.add(legExercises.get(random.nextInt(legExercises.size())));
        }
    }

    // Add an exercise to the current workout
    public void addExercise(Exercise exercise) {
        this.currentExercises.add(exercise);
    }

    // Get the list of current exercises in the workout
    public ArrayList<Exercise> getExercises() {
        return currentExercises;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date=date;
    }
}
