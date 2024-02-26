package com.example.Season;
import com.example.Season.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    //UserDTO updateById(Integer id);
}
