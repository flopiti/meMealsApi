package com.memeals.meMealsApi.UserMealLike;

import com.memeals.meMealsApi.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserMealLikeRepository extends JpaRepository<UserMealLike, Long> {

    List<UserMealLike> findByUser(User user);

}
