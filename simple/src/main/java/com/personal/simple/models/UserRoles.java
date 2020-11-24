package com.personal.simple.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "userroles")
@IdClass(UserRolesId.class)
public class UserRoles extends Auditable implements Serializable {
    
    // Fields
    private User user;

    private Role role;

    // Constructors
    public UserRoles() {

    }

    public UserRoles (User user, Role role) {

        this.user = user;
        this.role = role;
    }

    // Getters and Setters
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}