package com.example.maxfitdemoapp.model;

import android.app.Activity;

import java.io.File;

public class GoalLift {
    private String name;
    private double previousMax;
    private double currentMax;
    private double goalMax;

    // Constructor to initialize a GoalLift instance
    public GoalLift(String name, double previousMax, double currentMax, double goalMax) {
        this.name = name;
        this.previousMax = previousMax;
        this.currentMax = currentMax;
        this.goalMax = goalMax;
    }

    public void setPreviousMax(double previousMax) {
        this.previousMax = previousMax;
    }

    public void setCurrentMax(double currentMax) {
        this.currentMax = currentMax;
    }

    public void setGoalMax(double goalMax) {
        this.goalMax = goalMax;
    }

    // Calculate the percentage change in the max lift
    public double calculatePercentChange() {
        if (previousMax == 0) {
            return 0;
        }
        return ((currentMax - previousMax) / previousMax) * 100;
    }

    // Update the current max lift
    public void updateMax(double newMax) {
        this.currentMax = newMax;
    }

    public String getName() { return name; }

    // Get the previous max lift value
    public double getPreviousMax() {
        return previousMax;
    }

    // Get the current max lift value
    public double getCurrentMax() {
        return currentMax;
    }

    // Get the goal max lift value
    public double getGoalMax() {
        return goalMax;
    }

}

