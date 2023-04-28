package com.memeals.meMealsApi.Meal;

import lombok.Data;

@Data
public class MealIngredientDTO {
    private Long ingredientId;
    private Double quantity;
    private String unitOfMeasurement;

    // Add getters and setters
}