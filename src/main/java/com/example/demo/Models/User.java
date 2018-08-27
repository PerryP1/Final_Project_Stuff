package com.example.demo.Models;

import com.example.demo.Models.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "USER_DATA")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "username")
    private String username;

//    @ManyToMany()
//    private Set<User> friends;
//
//
//    public User() {
//        this.friends = new HashSet<User>();
//    }


    public User() {
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User(String email, String password, String first_name, String last_name, boolean enabled, String username) {// this is a  loaded constructor
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.enabled = enabled;
        this.username = username;
    }



    public String getPassword() {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword=passwordEncoder.encode(password);
//        System.out.println(hashedPassword);
//        this.password=hashedPassword;
        return password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}