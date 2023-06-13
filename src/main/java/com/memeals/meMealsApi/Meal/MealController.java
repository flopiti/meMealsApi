package com.memeals.meMealsApi.Meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.memeals.meMealsApi.Auth0.AuthService;
import com.memeals.meMealsApi.Exceptions.MealNotFoundException;
import com.memeals.meMealsApi.User.User;
import com.memeals.meMealsApi.User.UserService;
import com.memeals.meMealsApi.UserMealLike.UserMealLikeService;

import java.util.List;

@RestController
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @Autowired
    private UserMealLikeService userMealLikeService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<MealDTO> createMeal(@RequestBody MealDTO meal) {
        Meal savedMeal = mealService.saveMeal(meal);
        return new ResponseEntity<>(mealService.convertToDTO(savedMeal), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable Long id) {
        Meal meal = mealService.getMealById(id);
        if (meal != null) {
            return new ResponseEntity<>(meal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<MealDTO>> getAllMeals() {
        List<MealDTO> mealDTOs = mealService.getAllMeals();
        return new ResponseEntity<>(mealDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealDTO> updateMeal(@PathVariable Long id, @RequestBody MealDTO meal) {
        return new ResponseEntity<>(mealService.convertToDTO(mealService.updateMeal(meal)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        Meal meal = mealService.getMealById(id);
        userMealLikeService.unlikeAll( meal);
        mealService.deleteMeal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
