package com.example.musicstreamingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
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

    @OneToMany(mappedBy = "userProfile", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Playlist> playlists = new ArrayList<>();
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

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
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
