package com.memeals.meMealsApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import java.util.Map;
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;

@Service
public class AuthService {
    public String getUserId() {
        Authentication authentification = SecurityContextHolder.getContext().getAuthentication();  
        if (authentification instanceof AuthenticationJsonWebToken) {
            String token = authentification.getCredentials().toString();
            DecodedJWT jwt = JWT.decode(token);
            String id = jwt.getClaim("sub").asString();            
            if (id != null) {;
              return id;
            } else {
                System.out.println("User ID not found in the JWT token");
              return null;
            }
        }
        return null;
    }
}