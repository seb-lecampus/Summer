package com.example.Season.controller;

import com.example.Season.User2;
import com.example.Season.User2Repository;
import com.example.Season.rest_param.UserParam2;
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
}
