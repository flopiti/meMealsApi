package com.memeals.meMealsApi.Meal;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.memeals.meMealsApi.Ingredient.Ingredient;
import com.memeals.meMealsApi.Ingredient.IngredientRepository;
import com.memeals.meMealsApi.MealIngredient.IngredientMeal;
import com.memeals.meMealsApi.MealIngredient.IngredientMealRepository;
import com.memeals.meMealsApi.MealIngredient.MealIngredientDTO;

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
        Meal meal = new Meal();
        List<IngredientMeal> mealIngredients = new ArrayList<>();
        meal.setMealName(mealDTO.getMealName());
        meal.setIconUrl(mealDTO.getIconUrl());
        Meal savedMeal = mealRepository.save(meal);
        if (mealDTO.getMealIngredients() != null) {
            for (MealIngredientDTO mealIngredientDTO : mealDTO.getMealIngredients()) {
                Ingredient ingredient = ingredientRepository.findById(mealIngredientDTO.getIngredientId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient ID"));
                        IngredientMeal mealIngredient = mealIngredientRepository.save(new IngredientMeal(null, meal, ingredient, mealIngredientDTO.getQuantity(),mealIngredientDTO.getUnitOfMeasurement()));
                mealIngredients.add(mealIngredient);
            }
        }
        savedMeal.setMealIngredients(mealIngredients);
        return savedMeal;
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
    public Meal updateMeal(MealDTO mealDTO) {
        Meal editedMeal = new Meal();
        editedMeal.setId(mealDTO.getId());
        editedMeal.setMealName(mealDTO.getMealName());
        editedMeal.setIconUrl(mealDTO.getIconUrl());
        List<IngredientMeal> editedIngredientMeals = new ArrayList<>();
        Optional<Meal> existingMeal = mealRepository.findById(mealDTO.getId());
        if (existingMeal.isPresent()) {
            Hibernate.initialize(existingMeal.get().getMealIngredients());
        }

        if (mealDTO.getMealIngredients() != null) {
            for (MealIngredientDTO mealIngredientDTO : mealDTO.getMealIngredients()) {
                boolean alreadyExists = mealIngredientDTO.getId() != null;

                if(!alreadyExists){
                Ingredient ingredient = ingredientRepository.findById(mealIngredientDTO.getIngredientId())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient ID"));

                IngredientMeal mealIngredient = new IngredientMeal(null, existingMeal.get(), ingredient, mealIngredientDTO.getQuantity(),mealIngredientDTO.getUnitOfMeasurement());
                IngredientMeal newOne =  mealIngredientRepository.save(mealIngredient);
                editedIngredientMeals.add(newOne);
                }
                else{
                    editedIngredientMeals.add(convertFromDTOIngredientMeal(mealIngredientDTO));
                }
            }
        }
        editedMeal.setMealIngredients(editedIngredientMeals);
        return mealRepository.save(editedMeal);
    }

    public IngredientMeal convertFromDTOIngredientMeal(MealIngredientDTO mealIngredientDTO) {
        IngredientMeal mealIngredient = mealIngredientRepository.findById(mealIngredientDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid meal ingredient ID"));
        return mealIngredient;
    }

    @Override
    public void deleteMeal(Long id) {
        mealIngredientRepository.deleteByMealId(id);
        mealRepository.deleteById(id);
    }

    public Meal convertFromDTOMeal(MealDTO mealDTO) {
        Meal meal = new Meal(mealDTO.getMealName(), mealDTO.getIconUrl());
        meal.setId(mealDTO.getId());
        List<IngredientMeal> mealIngredients = new ArrayList<>();
        for (MealIngredientDTO mealIngredientDTO : mealDTO.getMealIngredients()) {
            Ingredient ingredient = ingredientRepository.findById(mealIngredientDTO.getIngredientId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid ingredient ID"));

            IngredientMeal mealIngredient = new IngredientMeal(null, meal, ingredient, mealIngredientDTO.getQuantity(),mealIngredientDTO.getUnitOfMeasurement());
            mealIngredients.add(mealIngredient);
        }
        meal.setMealIngredients(mealIngredients);
        return meal;
    }
     
    public MealDTO convertToDTO(Meal meal) {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setId(meal.getId());
        mealDTO.setMealName(meal.getMealName());
        mealDTO.setIconUrl(meal.getIconUrl());
        List<MealIngredientDTO> mealIngredientDTOs = new ArrayList<>();
        List<IngredientMeal> mealIngredients = meal.getMealIngredients();
        for (IngredientMeal mealIngredient : mealIngredients) {
            MealIngredientDTO mealIngredientDTO = new MealIngredientDTO();
            mealIngredientDTO.setId(mealIngredient.getId());
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
