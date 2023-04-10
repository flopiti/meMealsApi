package com.memeals.meMealsApi.ScheduledMeal;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduledMealRepository extends JpaRepository<ScheduledMeal, Long> {
    List<ScheduledMeal> findByUserId(Long userId);

}