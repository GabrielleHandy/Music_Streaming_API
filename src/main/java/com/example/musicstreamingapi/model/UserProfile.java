package com.example.musicstreamingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a user profile in the application.
 */
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
    /**
     * Represents the association between a user profile and related entities, such as a user and playlists.
     */
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne
    private User user;

    /**
     * Represents a list of playlists associated with this user profile.
     * This field establishes a one-to-many relationship between the user profile and playlists.
     * It allows multiple playlists to be associated with a user profile.
     * The `mappedBy` attribute indicates that the "userProfile" field in the "Playlist" entity is the owning side
     * of the relationship. The "userProfile" field in the "Playlist" entity should match the name of this field
     * ("playlists") here.
     * The "orphanRemoval" attribute is set to true, which means that if a playlist is removed from this list, it
     * will be deleted from the database if it's no longer associated with any user profile.
     * The "LazyCollectionOption.FALSE" annotation is used to specify that playlists should be eagerly fetched
     * (loaded) from the database when querying the user profile.
     */
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
