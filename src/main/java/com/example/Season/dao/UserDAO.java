package com.example.Season.dao;

import com.example.Season.dto.UserDTO;

import java.sql.SQLException;
import java.util.Collection;

public interface UserDAO {
    UserDTO getUserById(int id);
    Collection<UserDTO> getAllUsers() throws SQLException;
    void addUser(UserDTO user);
    void updateUser(int id, UserDTO user);
    void deleteUser(int id);
}

