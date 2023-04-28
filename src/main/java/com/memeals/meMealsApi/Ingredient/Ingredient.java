package com.memeals.meMealsApi.Ingredient;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.memeals.meMealsApi.Meal.IngredientMeal;

import lombok.Data;

import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<IngredientMeal> mealIngredients;
}