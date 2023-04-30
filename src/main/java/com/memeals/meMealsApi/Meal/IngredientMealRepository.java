package com.memeals.meMealsApi.Meal;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientMealRepository extends JpaRepository<IngredientMeal, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM IngredientMeal im WHERE im.meal.id = ?1")
    void deleteByMealId(Long mealId);
}