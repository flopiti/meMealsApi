package com.memeals.meMealsApi;

public class ScheduledMealNotFoundException extends RuntimeException {
    public ScheduledMealNotFoundException(String message) {
        super(message);
    }
}