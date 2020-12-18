package com.personal.simple.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "roles")
public class Role extends Auditable {

    // Define fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @Column( nullable = false, unique = true)
    private String name;

    @OneToMany(
        mappedBy = "role", 
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonIgnoreProperties(value = "roles", allowSetters = true)
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