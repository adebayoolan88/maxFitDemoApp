package com.example.maxfitdemoapp.model;

import android.app.Activity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person implements java.io.Serializable {
    private String name;
    private int age;
    private double height;
    private double weight;
    private String gender;
    private Workout currentWorkout;
    private ArrayList<Workout> workoutHistory;
    private ArrayList<GoalLift> goalLifts;

    // Constructor to initialize a Person instance
    public Person(String name, int age, double height, double weight, String gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.workoutHistory = new ArrayList<>();
        this.goalLifts = new ArrayList<>();
    }

    // Getter methods
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public String getGender() { return gender; }

    // Add a workout to the person's workout history
    public void updateWorkoutHistory(Workout workout) {
        this.workoutHistory.add(workout);
    }

    // Set the current workout
    public void setCurrentWorkout(Workout workout) {
        this.currentWorkout = workout;
    }

    // Get the current workout
    public Workout getCurrentWorkout() {
        return currentWorkout;
    }

    // Get the workout history
    public List<Workout> getWorkoutHistory() {
        return workoutHistory;
    }

    // Add a new goal lift to the person's goals
    public void addGoalLift(GoalLift goalLift) {
        this.goalLifts.add(goalLift);
    }

    // Get the list of goal lifts
    public List<GoalLift> getGoalLifts() {
        return goalLifts;
    }

    /**
     * Saves the person's data to a CSV file named "person.csv".
     *
     * @param activity The Activity instance to access files.
     */
    public void savePerson(Activity activity) {
        try {
            // Save basic person data
            File outputFile = new File(activity.getFilesDir(), "person.csv");
            try (FileWriter writer = new FileWriter(outputFile)) {
                // Write the header
                writer.write("name,age,height,weight,gender\n");
                // Write the person's basic data
                writer.append(this.getName()).append(',')
                        .append(String.valueOf(this.getAge())).append(',')
                        .append(String.valueOf(this.getHeight())).append(',')
                        .append(String.valueOf(this.getWeight())).append(',')
                        .append(this.getGender()).append('\n');
            }

            // Save GoalLift data
            File goalLiftsFile = new File(activity.getFilesDir(), "goal_lifts.csv");
            try (FileWriter goalWriter = new FileWriter(goalLiftsFile)) {
                goalWriter.write("name,previousMax,currentMax,goalMax\n");
                for (GoalLift lift : goalLifts) {
                    goalWriter.append(lift.getName()).append(',')
                            .append(String.valueOf(lift.getPreviousMax())).append(',')
                            .append(String.valueOf(lift.getCurrentMax())).append(',')
                            .append(String.valueOf(lift.getGoalMax())).append('\n');
                }
            }

            // Save WorkoutHistory data
            File workoutHistoryFile = new File(activity.getFilesDir(), "workout_history.csv");
            try (FileWriter workoutWriter = new FileWriter(workoutHistoryFile)) {
                int totalWorkouts = workoutHistory.size();
                int startIndex = Math.max(totalWorkouts - 4, 0);
                List<Workout> lastFiveWorkouts = workoutHistory.subList(startIndex, totalWorkouts);

                for (Workout wh : lastFiveWorkouts) {
                    workoutWriter.write("date," + wh.getDate() + "\n");
                    for (Exercise exercise : wh.getExercises()) {
                        workoutWriter.append(exercise.getName()).append(',')
                                .append(String.valueOf(exercise.getReps())).append(',')
                                .append(String.valueOf(exercise.getSets())).append('\n');
                    }
                    workoutWriter.append('\n');
                }
            }

        } catch (IOException e) {
            Log.e("Person", "Error saving person data", e);
        }
    }


    /**
     * Loads the person's data from a CSV file named "person.csv".
     * If the file doesn't exist, it copies a default one from assets.
     *
     * @param activity The Activity instance to access files.
     * @return The loaded Person object, or null if an error occurs.
     */
    public static Person loadPerson(Activity activity) {
        try {
            File internalFile = new File(activity.getFilesDir(), "person.csv");
            if (!internalFile.exists()) {
                InputStream is = activity.getAssets().open("person.csv");
                FileOutputStream fos = new FileOutputStream(internalFile);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                is.close();
                fos.close();
            }

            Scanner personScanner = new Scanner(internalFile);
            if (personScanner.hasNextLine()) {
                personScanner.nextLine(); // Skip header
            }

            Person person = null;
            if (personScanner.hasNextLine()) {
                String line = personScanner.nextLine();
                String[] values = line.split(",");

                String name = values[0];
                int age = Integer.parseInt(values[1]);
                double height = Double.parseDouble(values[2]);
                double weight = Double.parseDouble(values[3]);
                String gender = values[4];

                person = new Person(name, age, height, weight, gender);
                personScanner.close();

                // Load goal_lifts.csv
                File goalLiftsFile = new File(activity.getFilesDir(), "goal_lifts.csv");
                if (goalLiftsFile.exists()) {
                    Scanner goalScanner = new Scanner(goalLiftsFile);
                    if (goalScanner.hasNextLine()) {
                        goalScanner.nextLine(); // Skip header
                    }
                    while (goalScanner.hasNextLine()) {
                        String goalLine = goalScanner.nextLine();
                        String[] goalValues = goalLine.split(",");
                        if (goalValues.length < 4) continue;
                        String liftName = goalValues[0];
                        double previousMax = Double.parseDouble(goalValues[1]);
                        double currentMax = Double.parseDouble(goalValues[2]);
                        double goalMax = Double.parseDouble(goalValues[3]);
                        GoalLift lift = new GoalLift(liftName, previousMax, currentMax, goalMax);
                        person.addGoalLift(lift);
                    }
                    goalScanner.close();
                }

                // Load workout_history.csv
                File workoutHistoryFile = new File(activity.getFilesDir(), "workout_history.csv");
                if (workoutHistoryFile.exists()) {
                    Scanner workoutScanner = new Scanner(workoutHistoryFile);
                    Workout currentWorkout = null;
                    while (workoutScanner.hasNextLine()) {
                        String workoutLine = workoutScanner.nextLine().trim();
                        if (workoutLine.isEmpty()) {
                            currentWorkout = null;
                            continue;
                        }
                        String[] workoutValues = workoutLine.split(",");
                        if (workoutValues.length == 2 && workoutValues[0].equalsIgnoreCase("date")) {
                            String date = workoutValues[1];
                            currentWorkout = new Workout();
                            currentWorkout.setDate(date); // Ensure Workout class has setDate method
                            person.updateWorkoutHistory(currentWorkout);
                        } else if (workoutValues.length == 3 && currentWorkout != null) {
                            String exerciseName = workoutValues[0];
                            int reps = Integer.parseInt(workoutValues[1]);
                            int sets = Integer.parseInt(workoutValues[2]);
                            Exercise exercise = new Exercise(exerciseName, reps, sets);
                            currentWorkout.addExercise(exercise);
                        }
                    }
                    workoutScanner.close();
                }

                return person;
            } else {
                Log.e("Person", "No person data found in CSV file");
                personScanner.close();
                return null;
            }
        } catch (Exception e) {
            Log.e("Person", "Error loading person data", e);
            return null;
        }
    }

    public static boolean checkPerson(Activity activity) {
        File inputFile = new File(activity.getFilesDir(), "person.csv");
        return inputFile.exists();
    }

    public static boolean checkGoalLifts(Activity activity) {
        File inputFile = new File(activity.getFilesDir(), "goal_lifts.csv");
        return inputFile.exists();
    }
}
