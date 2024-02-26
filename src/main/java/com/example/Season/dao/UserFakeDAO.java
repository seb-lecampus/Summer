package com.example.Season.dao;

import com.example.Season.dto.UserDTO;
import com.example.Season.fakedb.FakeDB;
import com.example.Season.fakedb.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserFakeDAO implements UserDAO {

    private FakeDB instance = new FakeDB(); // TODO singleton

    @Override
    public UserDTO getUserById(int id) {
        var u = instance.getUserById(id);
        return new UserDTO(u.getValue().firstName(), u.getValue().lastName());
    }

    @Override
    public Collection<UserDTO> getAllUsers() {
        return instance.getAllUsers().stream().map(e -> {
            System.out.println(e);
            return new UserDTO(e.getValue().firstName(), e.getValue().lastName());
        }).toList();
    }

    @Override
    public void addUser(UserDTO user) {
        instance.insertUser(new User(user.firstName(), user.lastName()));
    }

    @Override
    public void updateUser(int id, UserDTO user) {
        instance.update(id, new User(user.firstName(), user.lastName()));
    }

    @Override
    public void deleteUser(int id) {
        instance.delete(id);
    }
}
