package com.example.Season.controller;

import com.example.Season.entity.User2;
import com.example.Season.rest_param.UserParam2;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthoritiesController {
    @GetMapping("/user")
    public String signin(){
       return "USER";
    }

    @GetMapping("/admin")
    public String testouille(){
        return "ADMIN";
    }
}
