package com.memeals.meMealsApi.Auth0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import com.memeals.meMealsApi.User.User;
import com.memeals.meMealsApi.User.UserService;

@Service
public class AuthService {
  private final UserService userService;

  @Autowired
  public AuthService(UserService userService) {
    this.userService = userService;

  }
  public String getAuth0Id() {
      Authentication authentification = SecurityContextHolder.getContext().getAuthentication();  
      if (authentification instanceof AuthenticationJsonWebToken) {
          String token = authentification.getCredentials().toString();
          DecodedJWT jwt = JWT.decode(token);
          String id = jwt.getClaim("sub").asString().split("\\|")[1];            
          if (id != null) {;
            return id;
          } else {
              System.out.println("User ID not found in the JWT token");
            return null;
          }
      }
      return null;
  }

  public User getUser() {
      Authentication authentification = SecurityContextHolder.getContext().getAuthentication();  
      if (authentification instanceof AuthenticationJsonWebToken) {
          String token = authentification.getCredentials().toString();
          DecodedJWT jwt = JWT.decode(token);
          String id = jwt.getClaim("sub").asString().split("\\|")[1];            
          if (id != null) {
            User user = userService.getUserByAuth0Id(id).get();
            System.out.println(user.toString());
            return user;
          } else {
              System.out.println("User ID not found in the JWT token");
            return null;
          }
      }
      return null;
  }                         

  public String getUserId(){
    String auth0Id = getAuth0Id();
    if (auth0Id != null) {
      User user = userService.getUserByAuth0Id(auth0Id).get();
      if (user == null) {
        return null;
      }
      return user.getId().toString();
    } else {
      return null;
    }
  }
}