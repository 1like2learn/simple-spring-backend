package com.personal.simple.services;

import java.util.List;

import com.personal.simple.models.Role;

public interface RoleService {

    List<Role> findAll();

    Role findRoleById(long id);

    Role save(Role role);

    Role findByName(String name);

    public void deleteAll();

    Role update(long id, Role role);
}