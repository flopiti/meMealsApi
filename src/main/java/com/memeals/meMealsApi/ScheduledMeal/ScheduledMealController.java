package com.memeals.meMealsApi.ScheduledMeal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.memeals.meMealsApi.AuthService;
import com.memeals.meMealsApi.MealNotFoundException;

import java.time.LocalDate;
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
    public ScheduledMeal editScheduledMeal(@PathVariable Long scheduledMealId,
                                            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                            @RequestParam(value = "mealType", required = false) ScheduledMealType mealType) {
        return scheduledMealService.editScheduledMeal(scheduledMealId, date, mealType);
    }

    @ExceptionHandler(MealNotFoundException.class)
    public ResponseEntity<String> handleMealNotFoundException(MealNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

