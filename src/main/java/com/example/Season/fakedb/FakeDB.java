package com.example.Season.fakedb;

import org.springframework.stereotype.Repository;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class FakeDB {
    private int id = 0;
    private final Map<Integer, User> db = new HashMap<>();

    public Map.Entry<Integer, User> getUserById(int id){
        return new AbstractMap.SimpleEntry<>(id, db.get(id));
    }

    public int insertUser(User u){
        db.put(id++, u);
        return id;
    }

    public void update(int id, User u){
        db.put(id, u);
    }

    public void delete(int id){
        db.remove(id);
    }

    public Set<Map.Entry<Integer, User>> getAllUsers() {
        return db.entrySet();
    }
}
