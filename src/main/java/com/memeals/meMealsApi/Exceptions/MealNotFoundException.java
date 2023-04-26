package com.memeals.meMealsApi.Exceptions;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(String message) {
        super(message);
    }
    public MealNotFoundException(Long mealId) {
        super("Meal not found with id : " + mealId);
    }
}