package com.personal.simple.services;

import java.util.List;

import com.personal.simple.models.User;

public interface UserService {
    
    List<User> findAll();

    List<User> findByNameContaining(String username);

    User findUserById(long id);

    User findByName(String name);

    void delete(long id);

    User save(User user);

    User update(User user, long id);

    public void deleteAll();

}