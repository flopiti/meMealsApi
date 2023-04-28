package com.memeals.meMealsApi.Meal;

import javax.persistence.*;
import com.memeals.meMealsApi.Ingredient.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "ingredients")
@AllArgsConstructor
public class IngredientMeal {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meal_id", nullable = false)
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "unit_of_measurement")
    private String unitOfMeasurement;

}