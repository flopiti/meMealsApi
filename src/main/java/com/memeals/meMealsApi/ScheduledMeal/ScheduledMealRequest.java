package com.memeals.meMealsApi.ScheduledMeal;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledMealRequest {

    private Long userId;
    private Long mealId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    private ScheduledMealType mealType;

    public ScheduledMealRequest(Long mealId, LocalDate date, ScheduledMealType mealType) {
        this.mealId = mealId;
        this.date = date;
        this.mealType = mealType;
    }
}
