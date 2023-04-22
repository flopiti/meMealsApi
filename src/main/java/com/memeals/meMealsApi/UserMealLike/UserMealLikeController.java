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
import com.memeals.meMealsApi.AuthService;
import com.memeals.meMealsApi.MealNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/meal-likes")
public class UserMealLikeController {

    private final UserService userService;
    private final UserMealLikeService userMealLikeService;
    private final AuthService authService;

    @Autowired
    public UserMealLikeController(UserService userService, UserMealLikeService userMealLikeService, AuthService authService) {
        this.userService = userService;
        this.userMealLikeService = userMealLikeService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<List<UserMealLike>> getAllUserMealLikesByUserId() {
        String userId = authService.getAuth0Id();
        if(userId == null) {
            throw new MealNotFoundException("User not found");
        }
        User user = userService.getUserByAuth0Id(userId).orElseThrow(() -> new MealNotFoundException("User not found"));

        List<UserMealLike> userMealLikes = userMealLikeService.getMealLikes(user);
        return new ResponseEntity<>(userMealLikes, HttpStatus.OK);
    }

}
