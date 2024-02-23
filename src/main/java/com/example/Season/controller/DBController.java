package com.example.Season.controller;

import com.example.Season.dto.UserDTO;
import com.example.Season.rest_param.UserParam;
import com.example.Season.dao.UserFakeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DBController {
    @Autowired private UserFakeDAO db;
    @GetMapping("/User")
    public Collection<UserDTO> getAllUsers(){
        return db.getAllUsers();
    }

    @PostMapping("/User")
    public void insertUser(@RequestBody UserParam user){
        db.addUser(new UserDTO(user.firstName(), user.lastName()));
    }
}
