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
    public ResponseEntity<Meal> createMeal(@RequestBody MealDTO meal) {
        Meal savedMeal = mealService.saveMeal(meal);
        return new ResponseEntity<>(savedMeal, HttpStatus.CREATED);
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
    public ResponseEntity<Meal> updateMeal(@PathVariable Long id, @RequestBody Meal meal) {
        meal.setId(id);
        Meal updatedMeal = mealService.updateMeal(meal);
        return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        String userId = authService.getAuth0Id();
        if (userId == null) {
            throw new MealNotFoundException("User not found");
        }
        User user = userService.getUserByAuth0Id(userId).orElseThrow(() -> new MealNotFoundException("User not found"));
        Meal meal = mealService.getMealById(id);

        System.out.println("meal: WHATTT");
        userMealLikeService.unlike(user, meal);
        mealService.deleteMeal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
