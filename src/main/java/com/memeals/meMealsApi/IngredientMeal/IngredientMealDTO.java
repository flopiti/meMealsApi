package com.memeals.meMealsApi.IngredientMeal;

import lombok.Data;

@Data
public class IngredientMealDTO {
    private Long id;
    private Long ingredientId;
    private Double quantity;
    private String unitOfMeasurement;
    private String ingredientName;
}