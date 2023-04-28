package com.memeals.meMealsApi.Meal;

import java.util.List;

public interface MealService {
    Meal saveMeal(MealDTO meal);
    Meal getMealById(Long id);
    List<MealDTO> getAllMeals();
    Meal updateMeal(Meal meal);
    void deleteMeal(Long id);
    List<MealDTO> convertToDTOList(List<Meal> meals);
}
