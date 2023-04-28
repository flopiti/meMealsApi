package com.memeals.meMealsApi.Meal;

import java.util.List;

import lombok.Data;

@Data
public class MealDTO {
    private String mealName;
    private String iconUrl;
    private List<MealIngredientDTO> mealIngredients;
}

   
