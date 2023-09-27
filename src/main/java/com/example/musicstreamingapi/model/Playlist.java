package com.example.musicstreamingapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;


@Entity
@Table(name = "playlists")
public class Playlist {
    @Getter
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private LocalDate dateCreated = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "userProfile_id", nullable = false)
    @JsonIgnore
    private UserProfile userProfile;

    //Many to Many Logic provided by Suresh Sigera and Bezdoker.com https://www.bezkoder.com/jpa-many-to-many/
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "playlist_songs",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    private Set<Song> songs = new HashSet<>();

    public Playlist() {
    }

    public Playlist(Long id, String name, LocalDate dateCreated, UserProfile userProfile, Set<Song> songs) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.userProfile = userProfile;
        this.songs = songs;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", userProfile=" + userProfile.getEmailAddress() +
                ", songs=" + songs +
                '}';
    }
}
