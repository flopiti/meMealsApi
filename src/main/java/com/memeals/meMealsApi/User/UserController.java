package com.memeals.meMealsApi.User;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> createUserPost(@RequestBody Map<String, Object> user) {
      System.out.println("Creating User: " + user);
      try {
          String email = (String) user.get("email");
          String auth0Id = (String) user.get("auth0Id");
          String username = (String) user.get("username");
  
          User newUser = userService.createUser(email, auth0Id, username);
          return new ResponseEntity<>(newUser, HttpStatus.CREATED);
      } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
}
