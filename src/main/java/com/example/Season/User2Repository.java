package com.example.Season;

import org.springframework.data.repository.CrudRepository;

public interface User2Repository extends CrudRepository<User2, Integer> {
    User2 findByUserName(String UserName);
}
