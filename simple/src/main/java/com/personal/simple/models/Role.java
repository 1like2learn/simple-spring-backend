package com.personal.simple.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends Auditable {

    // Define fields
    private long roleid;

    private String name;

    private Set<UserRoles> users = new HashSet<>();

    // Constructor functions
    public Role() {

    }

    public Role(String name) {
        this.name = name.toUpperCase();
    }

    // Getters and Setters
    public long getRoleid() {
        return this.roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRoles> getUsers() {
        return this.users;
    }

    public void setUsers(Set<UserRoles> users) {
        this.users = users;
    }

}