package com.example.Season.repository;
import com.example.Season.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    //UserDTO updateById(Integer id);
}
