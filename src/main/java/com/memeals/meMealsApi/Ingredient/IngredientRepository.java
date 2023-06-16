package com.memeals.meMealsApi.Ingredient;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    
    List<Ingredient> findByName(String name);
}
