package com.memeals.meMealsApi.Meal;

import java.util.List;

public interface MealService {
    Meal saveMeal(Meal meal);
    Meal getMealById(Long id);
    List<Meal> getAllMeals();
    Meal updateMeal(Meal meal);
    void deleteMeal(Long id);
}
