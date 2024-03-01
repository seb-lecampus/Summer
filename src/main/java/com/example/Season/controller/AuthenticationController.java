package com.example.Season.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @PostMapping("api/public/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationParams authenticationParams){
        try {
            Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(authenticationParams.username(), authenticationParams.password());

            Authentication authenticationResponse =
                    authenticationConfiguration.getAuthenticationManager().authenticate(authenticationRequest);

            if(authenticationResponse.isAuthenticated()) {
                var c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH, 1);

                String jwtToken = Jwts.builder()
                        .claim("name", authenticationParams.username)
                        //.claim("authorities", TROLOLOL)
                        .setSubject(authenticationParams.username)
                        //.setId(UUID.randomUUID().toString())
                        //.setIssuedAt(Date.from(now))
                        .setExpiration(c.getTime())
                        .compact();
                var h = new HttpHeaders();
                        h.set(HttpHeaders.AUTHORIZATION, jwtToken);
                return ResponseEntity.ok()
                        .headers(h)
                        .body("");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    };

    public record AuthenticationParams(String username, String password){}
    public record AuthenticationResult(){}
}
