package com.example.Season.controller;

import com.example.Season.repository.User2Repository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
    AuthenticationManager authenticationManager;
    @Autowired
    User2Repository userRepository;

    @PostMapping("api/public/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationParams authenticationParams){
        try {
            Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(authenticationParams.username(), authenticationParams.password());

            Authentication authenticationResponse =
                    authenticationManager.authenticate(authenticationRequest);

            if(authenticationResponse.isAuthenticated()) {
                var tokenExp = Calendar.getInstance();
                    //tokenExp.add(Calendar.DAY_OF_MONTH, 1);
                    tokenExp.add(Calendar.SECOND, 1);
                var refreshExp = Calendar.getInstance();
                    refreshExp.add(Calendar.WEEK_OF_MONTH, 1);

                String jwtToken = Jwts.builder()
                        .claim("name", authenticationParams.username)
                        //.claim("authorities", TROLOLOL)
                        .subject(authenticationParams.username)
                        //.setId(UUID.randomUUID().toString())
                        //.setIssuedAt(Date.from(now))
                        .expiration(tokenExp.getTime())
                        .signWith(Keys.hmacShaKeyFor("ruhqsufgquigqiugiuhfgruhqsufgquigqiugiuhfgruhqsufgquigqiugiuhfgruhqsufgquigqiugiuhfg".getBytes()), Jwts.SIG.HS512)
                        .header().add("alg", Jwts.SIG.HS512.getId()).and()
                        .compact();

                String jwtRefresh = Jwts.builder()
                        .subject(authenticationParams.username)
                        .expiration(refreshExp.getTime())
                        .compact();

                userRepository.save(
                        userRepository.findByUserName(authenticationParams.username).setRefresh_token(jwtRefresh));

                var h = new HttpHeaders();
                        h.add(HttpHeaders.AUTHORIZATION, "Bearer "+jwtToken);
                        h.add(HttpHeaders.SET_COOKIE, "refresh_token="+jwtRefresh);
                        //h.add(HttpHeaders.COOKIE, new Cookie("COOKIE", jwtRefresh).toString());
                        //h.add(HttpHeaders.SET_COOKIE2, new Cookie("SET_COOKIE2", jwtRefresh).toString());
                return ResponseEntity.ok()
                        .headers(h)
                        .body("");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    };

    public record AuthenticationParams(@Pattern(regexp = "[a-z0-9A-Z]+") String username, @NotBlank String password){}
    public record AuthenticationResult(){}
}
