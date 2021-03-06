package com.personal.simple.models;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserRolesId implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 3337253187621767088L;

    private long user;

    private long role;

    public UserRolesId() {

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

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRolesId that = (UserRolesId) o;
        return user == that.user && role == that.role;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}