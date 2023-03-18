package com.memeals.meMealsApi.ScheduledMeal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.memeals.meMealsApi.MealNotFoundException;
import com.memeals.meMealsApi.ScheduledMealNotFoundException;
import com.memeals.meMealsApi.Meal.Meal;
import com.memeals.meMealsApi.Meal.MealRepository;


@Service
public class ScheduledMealServiceImpl implements ScheduledMealService {

    private final ScheduledMealRepository scheduledMealRepository;
    private final MealRepository mealRepository;

    public ScheduledMealServiceImpl(ScheduledMealRepository scheduledMealRepository, 
                                     MealRepository mealRepository) {
        this.scheduledMealRepository = scheduledMealRepository;
        this.mealRepository = mealRepository;
    }

    @Override
    public ScheduledMeal scheduleMeal(Long mealId, LocalDate date, MealType mealType) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new MealNotFoundException("Meal not found with id " + mealId));

        ScheduledMeal scheduledMeal = new ScheduledMeal();
        scheduledMeal.setMeal(meal);
        scheduledMeal.setDate(date);
        scheduledMeal.setMealType(mealType);

        return scheduledMealRepository.save(scheduledMeal);
    }

    @Override
    public void deleteScheduledMeal(Long scheduledMealId) throws ScheduledMealNotFoundException {
        scheduledMealRepository.deleteById(scheduledMealId);
    }

    @Override
    public ScheduledMeal editScheduledMeal(Long scheduledMealId, LocalDate date, MealType mealType) throws ScheduledMealNotFoundException {
        Optional<ScheduledMeal> optionalScheduledMeal = scheduledMealRepository.findById(scheduledMealId);
        if (!optionalScheduledMeal.isPresent()) {
            throw new ScheduledMealNotFoundException("Scheduled Meal not found for Id: " + scheduledMealId);
        }
        ScheduledMeal scheduledMeal = optionalScheduledMeal.get();
        scheduledMeal.setDate(date);
        scheduledMeal.setMealType(mealType);
        return scheduledMealRepository.save(scheduledMeal);
    }

    @Override
    public List<ScheduledMeal> getAll() {
        List<ScheduledMeal> scheduledMeals = scheduledMealRepository.findAll();
        for (ScheduledMeal scheduledMeal : scheduledMeals) {
            Meal meal = mealRepository.findById(scheduledMeal.getMeal().getId())
                    .orElseThrow(() -> new MealNotFoundException(scheduledMeal.getMeal().getId()));
            scheduledMeal.setMeal(meal);
        }
        return scheduledMeals;
        }
}
