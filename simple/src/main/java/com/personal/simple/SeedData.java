package com.personal.simple;

import com.personal.simple.models.Role;
import com.personal.simple.models.User;
import com.personal.simple.models.UserRoles;
import com.personal.simple.services.RoleService;
import com.personal.simple.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData implements CommandLineRunner{
    
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        
        roleService.deleteAll();
        userService.deleteAll();

        Role r1 = new Role("ADMIN");
        Role r2 = new Role("USER");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        User u1 = new User("admin", "password", "admin@test.com");

        u1.getRoles().add(new UserRoles(u1, r1));
        u1.getRoles().add(new UserRoles(u1, r2));

        u1 = userService.save(u1);
    }


}