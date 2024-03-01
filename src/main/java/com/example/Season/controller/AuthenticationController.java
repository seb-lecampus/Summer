package com.example.Season.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @PostMapping("api/public/login")
    public AuthenticationResult login(@RequestBody AuthenticationParams authenticationParams){
        try {
            Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(authenticationParams.username(), authenticationParams.password());

            Authentication authenticationResponse =
                    authenticationConfiguration.getAuthenticationManager().authenticate(authenticationRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };

    public record AuthenticationParams(String username, String password){}
    public record AuthenticationResult(){}
}
