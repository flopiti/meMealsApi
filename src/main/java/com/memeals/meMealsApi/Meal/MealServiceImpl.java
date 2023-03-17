package com.memeals.meMealsApi.Meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public Meal getMealById(Long id) {
        Optional<Meal> meal = mealRepository.findById(id);
        if (meal.isPresent()) {
            return meal.get();
        }
        return null;
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    @Override
    public Meal updateMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }
}
