package com.example.Season.controller;

import com.example.Season.dao.UserMySQLDAO;
import com.example.Season.dto.UserDTO;
import com.example.Season.rest_param.UserParam;
import com.example.Season.dao.UserFakeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;

@RestController
public class DBController {
    @Autowired private UserMySQLDAO db;
    @GetMapping("/User")
    public Collection<UserDTO> getAllUsers() {
        return db.getAllUsers();
    }

    @PostMapping("/User")
    public void insertUser(@RequestBody UserParam user){
        db.addUser(new UserDTO(user.firstName(), user.lastName()));
    }

    @GetMapping("/User/{id}")
    public UserDTO updateUser(@PathVariable int id){
        return db.getUserById(id);
    }

    @PutMapping("/User/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserParam user){
        db.updateUser(id, new UserDTO(user.firstName(), user.lastName()));
    }

    @DeleteMapping("/User/{id}")
    public void deleteUser(@PathVariable int id){
        db.deleteUser(id);
    }
}
