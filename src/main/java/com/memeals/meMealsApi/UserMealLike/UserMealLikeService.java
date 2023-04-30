package com.memeals.meMealsApi.UserMealLike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.memeals.meMealsApi.User.User;
import java.util.List;
import com.memeals.meMealsApi.Meal.Meal;


@Service
public class UserMealLikeService {

    private final UserMealLikeRepository userMealLikeRepository;

    @Autowired
    public UserMealLikeService( UserMealLikeRepository userMealLikeRepository) {
        this.userMealLikeRepository = userMealLikeRepository;
    }

    public List<Meal> getMealLikes(User user) {
        List<UserMealLike> mealsLike = userMealLikeRepository.findByUser(user);
        List<Meal> meals = mealsLike.stream().map(mealLike -> mealLike.getMeal()).toList();
        return meals;
    }

    public UserMealLike like(User user, Meal meal) {
        UserMealLike userMealLike = new UserMealLike();
        userMealLike.setUser(user);
        userMealLike.setMeal(meal);
        return userMealLikeRepository.save(userMealLike);
    }

    public void unlike(User user, Meal meal) {
        UserMealLike userMealLike;
        try{
            userMealLike = userMealLikeRepository.findByUserAndMeal(user, meal);
            userMealLikeRepository.delete(userMealLike);
        }
        catch(Exception e){
            System.out.println("UserMealLike not found");
        }
    }
}