package com.memeals.meMealsApi.UserMealLike;

import com.memeals.meMealsApi.User.User;
import com.memeals.meMealsApi.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.memeals.meMealsApi.Auth0.AuthService;
import com.memeals.meMealsApi.Exceptions.MealNotFoundException;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.memeals.meMealsApi.Meal.Meal;
import com.memeals.meMealsApi.Meal.MealService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/meal-likes")
public class UserMealLikeController {

    private final UserService userService;
    private final UserMealLikeService userMealLikeService;
    private final AuthService authService;
    private final MealService mealService;

    @Autowired
    public UserMealLikeController(UserService userService, UserMealLikeService userMealLikeService,
            AuthService authService, MealService mealService) {
        this.userService = userService;
        this.userMealLikeService = userMealLikeService;
        this.authService = authService;
        this.mealService = mealService;
    }

    @GetMapping
    public ResponseEntity<List<Meal>> getAllUserMealLikesByUserId() {
        String userId = authService.getAuth0Id();
        if(userId == null) {
            throw new MealNotFoundException("User not found");
        }
        User user = userService.getUserByAuth0Id(userId).orElseThrow(() -> new MealNotFoundException("User not found"));
        List<Meal> userMealLikes = userMealLikeService.getMealLikes(user);
        return new ResponseEntity<>(userMealLikes, HttpStatus.OK);
    }

    @PostMapping("/{mealId}")
    public ResponseEntity<UserMealLike> likeMeal(@PathVariable("mealId") Long mealId) {
        String userId = authService.getAuth0Id();
        if (userId == null) {
            throw new MealNotFoundException("User not found");
        }
        User user = userService.getUserByAuth0Id(userId).orElseThrow(() -> new MealNotFoundException("User not found"));
        Meal meal = mealService.getMealById(mealId);
        UserMealLike userMealLike = userMealLikeService.like(user, meal);
        return new ResponseEntity<>(userMealLike, HttpStatus.CREATED);
    }

    @DeleteMapping("/{mealId}")
    public ResponseEntity<Void> unlikeMeal(@PathVariable("mealId") Long mealId) {
        String userId = authService.getAuth0Id();
        if (userId == null) {
            throw new MealNotFoundException("User not found");
        }
        User user = userService.getUserByAuth0Id(userId).orElseThrow(() -> new MealNotFoundException("User not found"));
        Meal meal = mealService.getMealById(mealId);

        userMealLikeService.unlike(user, meal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
