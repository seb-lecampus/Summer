package com.example.Season.dao;

import com.example.Season.dto.UserDTO;
import org.springframework.stereotype.Repository;
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

@Repository
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
        try {
            PreparedStatement ps = getCon().prepareStatement("SELECT * FROM user WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
                return new UserDTO(rs.getString("firstName"), rs.getString("lastName"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        List<UserDTO> result = null;
        try {
            PreparedStatement ps = getCon().prepareStatement("SELECT * FROM user");
            ResultSet rs = ps.executeQuery();
            result = new ArrayList<>(rs.getFetchSize());
            while (rs.next()) {
                result.add(new UserDTO(rs.getString("firstName"), rs.getString("lastName")));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void addUser(UserDTO user) {
        try {
            PreparedStatement ps = getCon().prepareStatement("INSERT INTO user(firstName, lastName) VALUES(?, ?)");
            ps.setString(1, user.firstName());
            ps.setString(2, user.lastName());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateUser(int id, UserDTO user) {
        try {
            PreparedStatement ps = getCon().prepareStatement("UPDATE user SET firstName=?, lastName=? WHERE id=?");
            ps.setString(1, user.firstName());
            ps.setString(2, user.lastName());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int id) {
        try {
            PreparedStatement ps = getCon().prepareStatement("DELETE FROM user WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
