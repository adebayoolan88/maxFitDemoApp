package com.example.maxfitdemoapp.model;

import java.io.Serializable;

public class Exercise implements Serializable {
    private String name;
    private int reps;
    private int sets;

    // Constructor to initialize an Exercise instance
    public Exercise(String name, int reps, int sets) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
    }

    // Update the number of reps and sets for the exercise
    public void updateRepsSets(int reps, int sets) {
        this.reps = reps;
        this.sets = sets;
    }

    public String toString(){
        return name + ": " + sets + "x" + reps;
    }

    public String getName(){
        return name;
    }

    public int getReps(){
        return reps;
    }
    public  int getSets(){
        return sets;
    }
}
