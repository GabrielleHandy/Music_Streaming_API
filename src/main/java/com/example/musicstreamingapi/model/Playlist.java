package com.example.musicstreamingapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a playlist entity that can contain a collection of songs.
 * This class is annotated with JPA annotations to map it to a database table named "playlists".
 */
@Entity
@Table(name = "playlists")
public class Playlist {
    /**
     * The unique identifier for the playlist.
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the playlist.
     */
    @Column
    private String name;

    /**
     * The date when the playlist was created. It is set to the current date by default.
     */
    @Column
    private LocalDate dateCreated = LocalDate.now();
    /**
     * The user profile associated with this playlist.
     */
    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonIgnore
    private UserProfile userProfile;
    /**
     * The set of songs contained in the playlist.
     */
    //Many to Many Logic provided by Suresh Sigera and Bezdoker.com https://www.bezkoder.com/jpa-many-to-many/
    @ManyToMany
    @JoinTable(name = "playlist_songs",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "song_id") })
    private Set<Song> songs = new HashSet<>();
    /**
     * Default constructor for the Playlist class.
     */
    public Playlist() {
    }

    /**
     * Parameterized constructor for the Playlist class.
     *
     * @param id           The unique identifier for the playlist.
     * @param name         The name of the playlist.
     * @param dateCreated  The date when the playlist was created.
     * @param userProfile  The user profile associated with this playlist.
     * @param songs        The set of songs contained in the playlist.
     */

    public Playlist(Long id, String name, LocalDate dateCreated, UserProfile userProfile, Set<Song> songs) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
        this.userProfile = userProfile;
        this.songs = songs;
    }

    // Getter and setter methods for the fields...

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
    public void addSong(Song song) {
        this.songs.add(song);
        song.getPlaylists().add(this);
    }

    public void removeSong(long songId) {
        Song songRemove = this.songs.stream().filter(song -> song.getId() == songId).findFirst().orElse(null);
        if (songRemove != null) {
            this.songs.remove(songRemove);
            songRemove.getPlaylists().remove(this);
        }
    }
    /**
     * Overrides the toString method to provide a string representation of the Playlist object.
     *
     * @return A string representation of the Playlist object.
     */

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Created by=" + userProfile +
                ", songs=" + songs +
                '}';
    }
}
