package com.personal.simple.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.personal.simple.exceptions.ResourceNotFoundException;
import com.personal.simple.models.User;
import com.personal.simple.repositories.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userrepos;

    // @Autowired
    // private RoleService roleService;

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        userrepos.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public List<User> findByNameContaining(String username) {

        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public User findUserById(long id) throws ResourceNotFoundException {
    
        return userrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public User findByName(String name) {
        
        User u = userrepos.findByUsername(name.toLowerCase());
        if ( u == null) {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return u;
    }

    @Transactional
    @Override
    public void delete(long id) {
        userrepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));

    }

    @Transactional
    @Override
    public User save(User user) {
        
        return userrepos.save(user);
    }

    @Transactional
    @Override
    public User update(User user, long id) {

        user.setUserid(id);

        return userrepos.save(user);
    }

    @Transactional
    @Override
    public void deleteAll() {
        
        userrepos.deleteAll();
    }
    
}