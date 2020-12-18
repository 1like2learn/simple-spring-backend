package com.personal.simple.services;

import java.util.ArrayList;
import java.util.List;

import com.personal.simple.exceptions.ResourceFoundException;
import com.personal.simple.exceptions.ResourceNotFoundException;
import com.personal.simple.models.Role;
import com.personal.simple.repositories.RoleRepository;
import com.personal.simple.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository rolerepos;

    @Autowired
    UserRepository userrepos;

    @Autowired 
    UserAuditing UserAuditing;

    @Override
    public List<Role> findAll() {

        List<Role> list = new ArrayList<>();

        rolerepos.findAll().iterator().forEachRemaining(list::add);

        return list;
    }

    @Override
    public Role findRoleById(long id) {

        return rolerepos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found."));
    }

    @Transactional
    @Override
    public Role save(Role role) {
        
        if (role.getUsers().size() > 0) {

            throw new ResourceFoundException("User Roles ar not updated through Role");
        }

        return rolerepos.save(role);
    }

    @Override
    public Role findByName(String name) {
        
        Role r = rolerepos.findByNameIgnoreCase(name);

        if ( r != null) {
            return r;
        } else {
            throw new ResourceNotFoundException("User with " + name + " not found.");
        }
    }

    @Transactional
    @Override
    public void deleteAll() {
        
        rolerepos.deleteAll();
    }

    @Transactional
    @Override
    public Role update(long id, Role role) {
        
        if (role.getName() == null){

            throw new ResourceNotFoundException("No role name found to update!");
        }

        if (role.getUsers().size() > 0){

            throw new ResourceFoundException("User Roles are not updated through Role. See endpoint POST: users/user/{userid}/role/{roleid}");
        }

        findRoleById(id); // see if user exists

        rolerepos.updateRoleName(UserAuditing.getCurrentAuditor().get(), id, role.getName());

        return findRoleById(id);
    }

    
    
}