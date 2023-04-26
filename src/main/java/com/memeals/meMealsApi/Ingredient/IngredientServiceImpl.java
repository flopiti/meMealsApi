package com.memeals.meMealsApi.Ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    public Ingredient saveIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
        
    }

    public Ingredient getIngredientById(Long id){
        return ingredientRepository.findById(id).get();
    }

    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll(); 
    }

    public void deleteIngredient(Long id){
        ingredientRepository.deleteById(id);
    }
}
