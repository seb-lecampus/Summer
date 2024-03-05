package com.example.Season.controller;

import com.example.Season.entity.User2;
import com.example.Season.repository.User2Repository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt")
public class JWTController {

    @Autowired
    private User2Repository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public void signin(@RequestBody UserParam2 u){
        userRepository.save(new User2(u.login(), passwordEncoder.encode(u.password())));
    }

    @GetMapping("/user/{username}")
    public User2 userbyname(@PathVariable String username){
        return userRepository.findByUserName(username);
    }

    @GetMapping("/test")
    public User2 testouille(){
        return null;
    }

    public record UserParam2 (@Pattern(regexp = "[a-z0-9A-Z]+") String login, @NotBlank String password) {

    }
}
