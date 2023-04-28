package com.memeals.meMealsApi.Meal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientMealRepository extends JpaRepository<IngredientMeal, Long> {
}