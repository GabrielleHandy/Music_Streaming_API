package com.example.musicstreamingapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Initliazes variables associated with the User class
 * returns a one to one relationship with User Profile
 * returns name, emailaddress, and password
 */
@Entity
@Table(name = "users")   // Define the "name" property and map it to a table column.
public class User {

    // Define the "id" property and mark it as the primary key with automatic generation.
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // Define the "emailAddress" property, map it to a table column, and make it unique.
    @Column(unique = true)
    private String emailAddress;

    // Define the "passWord" property, map it to a table column, and mark it for JSON serialization only.
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    // Define a one-to-one relationship with UserProfile and specify the foreign key.

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

    public User() {
    }

    public User(Long id, String name, String emailAddress, String password,UserProfile userProfile) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.userProfile = userProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    // toString() method
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }



}