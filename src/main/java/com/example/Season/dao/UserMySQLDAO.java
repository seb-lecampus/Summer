package com.example.Season.dao;

import com.example.Season.dto.UserDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMySQLDAO implements UserDAO {
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:6603/java", "root", "helloworld");

    public UserMySQLDAO() throws SQLException {
    }

    @Override
    public UserDTO getUserById(int id) {
        return null;
    }

    @Override
    public Collection<UserDTO> getAllUsers() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM user");
        ResultSet rs = ps.executeQuery();
        List<UserDTO> l = new ArrayList<>(rs.getFetchSize());
        while(rs.next()){
            l.add(new UserDTO(rs.getString("firstName"), rs.getString("lastName")));
        }
        return l;
    }

    @Override
    public void addUser(UserDTO user) {

    }

    @Override
    public void updateUser(int id, UserDTO user) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
