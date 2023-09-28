package com.example.musicstreamingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
/**
 * creates all variables associated with a user profile
 * returns a one to one relationship with User
 * returns firstname, lastname, and a profile bio
 */
@Entity
@Table(name = "profile")
public class UserProfile {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String profileBio;

    @JsonIgnore
    @OneToOne(mappedBy = "UserProfile")
    private User user;

    public UserProfile(){

    }

    public UserProfile(Long id, String firstName, String lastName, String profileBio, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileBio = profileBio;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfileBio (){
        return profileBio;
    }

    public void setProfileBio(String profileBio) {
        this.profileBio = profileBio;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profileBio='" + profileBio + '\'' +
                ", user=" + user +
                '}';
    }
}
