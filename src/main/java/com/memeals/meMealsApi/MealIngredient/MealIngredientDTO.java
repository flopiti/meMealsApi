package com.memeals.meMealsApi.MealIngredient;

import lombok.Data;

@Data
public class MealIngredientDTO {
    private Long ingredientId;
    private Double quantity;
    private String unitOfMeasurement;
    private String ingredientName;

}