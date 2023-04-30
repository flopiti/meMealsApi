package com.memeals.meMealsApi.Meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memeals.meMealsApi.Ingredient.Ingredient;
import com.memeals.meMealsApi.Ingredient.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private IngredientMealRepository mealIngredientRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Meal saveMeal(MealDTO mealDTO) {
        Meal meal = new Meal(mealDTO.getMealName(), mealDTO.getIconUrl());
        meal =  mealRepository.save(meal);
        if (mealDTO.getMealIngredients() != null) {
            for (MealIngredientDTO mealIngredientDTO : mealDTO.getMealIngredients()) {
                Ingredient ingredient = ingredientRepository.findById(mealIngredientDTO.getIngredientId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient ID"));

                IngredientMeal mealIngredient = new IngredientMeal(null, meal, ingredient, mealIngredientDTO.getQuantity(),mealIngredientDTO.getUnitOfMeasurement());
                mealIngredientRepository.save(mealIngredient);
            }
        }

        return meal;
    }

    public List<MealDTO> convertToDTOList(List<Meal> meals) {
        List<MealDTO> mealDTOs = new ArrayList<>();
        for (Meal meal : meals) {
            mealDTOs.add(convertToDTO(meal));
        }
        return mealDTOs;
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
    public List<MealDTO> getAllMeals() {
        List<Meal> meals = mealRepository.findAll();
        return convertToDTOList(meals);
    }

    @Override
    public Meal updateMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public void deleteMeal(Long id) {
        mealIngredientRepository.deleteByMealId(id);
        mealRepository.deleteById(id);
    }

    public MealDTO convertToDTO(Meal meal) {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setId(meal.getId());
        mealDTO.setMealName(meal.getMealName());
        mealDTO.setIconUrl(meal.getIconUrl());
        List<MealIngredientDTO> mealIngredientDTOs = new ArrayList<>();
        for (IngredientMeal mealIngredient : meal.getMealIngredients()) {
            MealIngredientDTO mealIngredientDTO = new MealIngredientDTO();
            mealIngredientDTO.setIngredientId(mealIngredient.getIngredient().getId());
            mealIngredientDTO.setQuantity(mealIngredient.getQuantity());
            mealIngredientDTO.setUnitOfMeasurement(mealIngredient.getUnitOfMeasurement());
            mealIngredientDTO.setIngredientName(mealIngredient.getIngredient().getName());
            mealIngredientDTOs.add(mealIngredientDTO);
        }
        mealDTO.setMealIngredients(mealIngredientDTOs); 
        return mealDTO;
    }
}
