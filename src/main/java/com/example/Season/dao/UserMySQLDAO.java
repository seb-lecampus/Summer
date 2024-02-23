package com.example.Season.dao;

import com.example.Season.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserMySQLDAO implements UserDAO {
    Connection conn;

    private Connection getCon() {
        if (conn == null)
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:6603/java", "root", "helloworld");
            } catch(Exception e) {
                System.err.println("Failed to connect");
            }
        return conn;
    }

    public UserMySQLDAO() throws SQLException {
    }

    @Override
    public UserDTO getUserById(int id) {
        return null;
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        try {
            PreparedStatement ps = getCon().prepareStatement("SELECT * FROM user");
            ResultSet rs = ps.executeQuery();
            List<UserDTO> l = new ArrayList<>(rs.getFetchSize());
            while (rs.next()) {
                l.add(new UserDTO(rs.getString("firstName"), rs.getString("lastName")));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addUser(UserDTO user) {
        //getCon().p
    }

    @Override
    public void updateUser(int id, UserDTO user) {

    }

    @Override
    public void deleteUser(int id) {

    }
}
