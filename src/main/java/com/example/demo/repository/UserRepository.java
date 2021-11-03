package com.example.demo.repository;

import com.example.demo.config.repository.BaseRepository;
import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author Alex
 * @date 2021/10/29 15:47
 */
@Repository
public interface UserRepository extends BaseRepository<User, String> {

    User getUserByUsername(String username);

}

