# Documentation for Java Classes and Methods

## Class: Person

### Constructor:
- **Person(String name, int age, double height, double weight, String gender)**
  - Initializes a new `Person` instance with the specified attributes (name, age, height, weight, gender). Adds predefined goal lifts (`Bench Press`, `Squat`, `Deadlift`, `Pullups`, `Cardio`).

### Methods:
- **void updateWorkoutHistory(Workout workout)**
  - Adds a workout to the `workoutHistory` list.
  - **Usage:** `person.updateWorkoutHistory(workout);`

- **void setCurrentWorkout(Workout workout)**
  - Sets the current workout for the person.
  - **Usage:** `person.setCurrentWorkout(workout);`

- **Workout getCurrentWorkout()**
  - Returns the current workout of the person.
  - **Usage:** `Workout current = person.getCurrentWorkout();`

- **List<Workout> getWorkoutHistory()**
  - Returns the list of all workouts in the person's history.
  - **Usage:** `List<Workout> history = person.getWorkoutHistory();`

- **Workout getWorkoutFromHistory(int index)**
  - Returns a specific workout from the workout history by index.
  - Throws `IndexOutOfBoundsException` if the index is invalid.
  - **Usage:** `Workout specificWorkout = person.getWorkoutFromHistory(1);`

- **void addGoalLift(GoalLift goalLift)**
  - Adds a new goal lift to the `goalLifts` list.
  - **Usage:** `person.addGoalLift(newGoalLift);`

- **List<GoalLift> getGoalLifts()**
  - Returns the list of goal lifts.
  - **Usage:** `List<GoalLift> goals = person.getGoalLifts();`

## Class: Workout

### Constructor:
- **Workout(String type)**
  - Initializes a new `Workout` instance with the specified type.
  - **Usage:** `Workout workout = new Workout("Strength");`

### Methods:
- **void loadExercises(Activity activity)**
  - Loads exercises from a resource file (categorized by chest, shoulders, back, arms, abs, legs).
  - **Usage:** `workout.loadExercises(activity);`

- **void addExercise(Exercise exercise)**
  - Adds an exercise to the current workout.
  - **Usage:** `workout.addExercise(exercise);`

- **ArrayList<Exercise> getExercises()**
  - Returns the list of current exercises in the workout.
  - **Usage:** `ArrayList<Exercise> exercises = workout.getExercises();`

## Class: Exercise

### Constructor:
- **Exercise(String name, int reps, int sets)**
  - Initializes a new `Exercise` instance with a specified name, reps, and sets.
  - **Usage:** `Exercise exercise = new Exercise("Push-Up", 15, 3);`

### Methods:
- **void updateRepsSets(int reps, int sets)**
  - Updates the number of reps and sets for the exercise.
  - **Usage:** `exercise.updateRepsSets(12, 4);`

## Class: GoalLift

### Constructor:
- **GoalLift(String name, double previousMax, double currentMax, double goalMax)**
  - Initializes a new `GoalLift` instance with specified values.
  - **Usage:** `GoalLift benchPress = new GoalLift("Bench Press", 100, 120, 150);`

### Methods:
- **double calculatePercentChange()**
  - Calculates and returns the percentage change in the maximum lift.
  - **Usage:** `double change = goalLift.calculatePercentChange();`

- **void updateMax(double newMax)**
  - Updates the current maximum value of the goal lift.
  - **Usage:** `goalLift.updateMax(130);`

- **double getPreviousMax()**
  - Returns the previous maximum value.
  - **Usage:** `double prevMax = goalLift.getPreviousMax();`

- **double getCurrentMax()**
  - Returns the current maximum value.
  - **Usage:** `double currMax = goalLift.getCurrentMax();`

- **double getGoalMax()**
  - Returns the goal maximum value.
  - **Usage:** `double goalMax = goalLift.getGoalMax();`

## Notes:
- The `savePerson` and `loadPerson` methods in the `Person` class are currently commented out. They can be used to save and load the `Person` object using Android's file system.
- The `generateRandomWorkout` method in the `Workout` class is a private method that randomly selects exercises from each category to create a full workout.

