package com.memeals.meMealsApi.Ingredient;

import java.util.List;

public interface IngredientService {
    
    Ingredient saveIngredient(Ingredient ingredient);

    Ingredient getIngredientById(Long id);

    List<Ingredient> getAllIngredients();

    void deleteIngredient(Long id);

    List<Ingredient> getIngredientByName(String name);
}
