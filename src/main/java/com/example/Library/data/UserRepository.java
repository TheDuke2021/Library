package com.example.Library.data;

import com.example.Library.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByUsername(String username);
}
