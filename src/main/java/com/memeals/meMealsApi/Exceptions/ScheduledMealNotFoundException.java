package com.memeals.meMealsApi.Exceptions;

public class ScheduledMealNotFoundException extends RuntimeException {
    public ScheduledMealNotFoundException(String message) {
        super(message);
    }
}