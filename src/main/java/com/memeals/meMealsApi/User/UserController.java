package com.memeals.meMealsApi.User;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.Optional;

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
          String username = (String) user.get("name");
          User newUser = userService.createUser(email, auth0Id, username);
          return new ResponseEntity<>(newUser, HttpStatus.CREATED);
      } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id) {
        Optional<User> userData = userService.getUserByAuth0Id(id);
        System.out.println("Getting user: " + id);
        System.out.println("Found user: " + userData);
        if (userData.isPresent()) {
            System.out.println("Found user: " + userData.get());
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        Optional<User> userData = userService.getUserByAuth0Id(id.toString());

        if (userData.isPresent()) {
            User existingUser = userData.get();
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setAuth0Id(updatedUser.getAuth0Id());

            return new ResponseEntity<>(userService.edit(existingUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}