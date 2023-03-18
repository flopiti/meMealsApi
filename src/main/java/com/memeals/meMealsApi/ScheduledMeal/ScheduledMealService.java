package com.memeals.meMealsApi.ScheduledMeal;

import java.time.LocalDate;
import java.util.List;

public interface ScheduledMealService {
    List<ScheduledMeal> getAll();
    ScheduledMeal scheduleMeal( Long mealId, LocalDate date, MealType mealType);
    void deleteScheduledMeal(Long scheduledMealId);
    ScheduledMeal editScheduledMeal(Long scheduledMealId, LocalDate date, MealType mealType);
}
