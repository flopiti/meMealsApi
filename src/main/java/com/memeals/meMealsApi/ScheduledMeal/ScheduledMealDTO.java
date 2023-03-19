package com.memeals.meMealsApi.ScheduledMeal;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ScheduledMealDTO {
    private Long id;
    private LocalDate date;
    private MealType mealType;
    private Long mealId;
}
