package com.memeals.meMealsApi.ScheduledMeal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.memeals.meMealsApi.Auth0.AuthService;
import com.memeals.meMealsApi.Exceptions.MealNotFoundException;
import com.memeals.meMealsApi.IngredientMeal.IngredientMealDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/scheduled-meals")
public class ScheduledMealController {
    private final ScheduledMealService scheduledMealService;

    private final AuthService authService;

    @Autowired
    public ScheduledMealController(ScheduledMealService scheduledMealService, AuthService authService) {
        this.scheduledMealService = scheduledMealService;
        this.authService = authService;
    }

    @GetMapping
    public List<ScheduledMealDTO> getAllForUser() {
        String userId = authService.getUserId();
        if(userId == null) {
            throw new MealNotFoundException("User not found");
        }
        System.out.println("User ID: " + userId);
        return scheduledMealService.getAllMyScheduledMeal(userId.equals("null") ? null : Long.parseLong(userId));
    }

    @GetMapping("/ingredients")
    public List<IngredientMealDTO> getAllForUserMealsIngredients(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate startDate, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate endDate) {
        String userId = authService.getUserId();
        if(userId == null) {
            throw new MealNotFoundException("User not found");
        }
        return scheduledMealService.getAllMyScheduledMealIngredients(Long.parseLong(userId), startDate, endDate);
    }

    @PostMapping
    public ScheduledMeal scheduleMeal(@RequestBody ScheduledMealRequest request) {
        String userId = authService.getUserId();
        if(userId == null) {
            throw new MealNotFoundException("User not found");
        }
        return scheduledMealService.scheduleMeal(request.getMealId(), request.getDate(), request.getMealType(), Long.parseLong(userId));
    }

    @DeleteMapping("/{scheduledMealId}")
    public void deleteScheduledMeal(@PathVariable Long scheduledMealId) {
        scheduledMealService.deleteScheduledMeal(scheduledMealId);
    }    

    @PutMapping("/{scheduledMealId}")
    public ScheduledMealDTO editScheduledMeal(@PathVariable Long scheduledMealId,
                                           @RequestBody ScheduledMealRequest scheduledMealRequest) {
    
        System.out.println("Scheduled Meal new date: " + scheduledMealRequest.getDate());
        return scheduledMealService.editScheduledMeal(scheduledMealId, scheduledMealRequest.getDate(), scheduledMealRequest.getMealType());
    }
    

    @ExceptionHandler(MealNotFoundException.class)
    public ResponseEntity<String> handleMealNotFoundException(MealNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

