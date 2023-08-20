package com.memeals.meMealsApi.Meal;

import java.util.List;

import com.memeals.meMealsApi.IngredientMeal.IngredientMealDTO;

import lombok.Data;

@Data
public class MealDTO {
    private Long id;
    private String mealName;
    private String iconUrl;
    private List<IngredientMealDTO> mealIngredients;
}

   
