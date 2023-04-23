package com.memeals.meMealsApi.UserMealLike;

import com.memeals.meMealsApi.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.memeals.meMealsApi.Meal.Meal;


@Repository
public interface UserMealLikeRepository extends JpaRepository<UserMealLike, Long> {

    List<UserMealLike> findByUser(User user);

    UserMealLike findByUserAndMeal(User user, Meal meal);
}