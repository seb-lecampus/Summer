package com.example.Season.controller;

import com.example.Season.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.Season.dao.UserMySQLDAO;
import com.example.Season.dto.UserDTO;
import com.example.Season.rest_param.UserParam;
import com.example.Season.dao.UserFakeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.StreamSupport;

@RestController
public class DBController {
    //@Autowired private UserMySQLDAO db;
    private static Logger LOGGER = LoggerFactory.getLogger(DBController.class);

    @Autowired private UserRepository userRepository;
    //UserRepository userRepository;
    @GetMapping("/User")
    public Collection<UserDTO> getAllUsers() {
        LOGGER.debug("GET /user");
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).map(e -> new UserDTO(e.getFirstName(), e.getLastName())).toList();
        //return db.getAllUsers();
    }

    @PostMapping("/User")
    public void insertUser(@RequestBody UserParam user){
        LOGGER.debug("POST /user");
        userRepository.save(new UserEntity(user.firstName(), user.lastName()));
        //db.addUser(new UserDTO(user.firstName(), user.lastName()));
    }

    @GetMapping("/User/{id}")
    public UserDTO getUserById(@PathVariable int id){
        LOGGER.debug("GET /user/"+id);
        var a = userRepository.findById(id).get();
        return new UserDTO(a.getFirstName(), a.getFirstName());
        //return db.getUserById(id);
    }

    @PutMapping("/User/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserParam user){
        LOGGER.debug("PUT /user/"+id);
        userRepository.save(new UserEntity(id, user.firstName(), user.lastName()));
        //db.updateUser(id, new UserDTO(user.firstName(), user.lastName()));
    }

    @DeleteMapping("/User/{id}")
    public void deleteUser(@PathVariable int id){
        LOGGER.debug("DELETE /user/"+id);
        userRepository.deleteById(id);
        //db.deleteUser(id);
    }
}
