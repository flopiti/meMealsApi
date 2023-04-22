package com.memeals.meMealsApi.UserMealLike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.memeals.meMealsApi.User.User;
import com.memeals.meMealsApi.User.UserService;
import java.util.List;


@Service
public class UserMealLikeService {

    private final UserMealLikeRepository userMealLikeRepository;

    @Autowired
    public UserMealLikeService( UserMealLikeRepository userMealLikeRepository) {
        this.userMealLikeRepository = userMealLikeRepository;
    }


    public List<UserMealLike> getMealLikes(User user) {
        return userMealLikeRepository.findByUser(user);
    }


}
