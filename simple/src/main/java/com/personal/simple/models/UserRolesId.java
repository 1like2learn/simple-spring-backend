package com.personal.simple.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserRolesId implements Serializable{
    
    private long user;

    private long role;

    public UserRolesId() {

    }

    public UserRolesId(long user, long role) {
        this.user = user;
        this.role = role;
    }

    public long getUser() {
        return this.user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getRole() {
        return this.role;
    }

    public void setRole(long role) {
        this.role = role;
    }

}