package com.memeals.meMealsApi.UserMealLike;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.memeals.meMealsApi.Meal.Meal;
import com.memeals.meMealsApi.User.User;
import lombok.Data;

@Data
@Entity
@Table(name = "user_meal_like")
public class UserMealLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}