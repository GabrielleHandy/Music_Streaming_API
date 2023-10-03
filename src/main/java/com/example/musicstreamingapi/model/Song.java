package com.example.musicstreamingapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;


    @ManyToOne
    @JoinColumn(name = "genre_id")
    @JsonIgnore
    private Genre genre;

    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "songs")
    @JsonIgnore
    private Set<Playlist> playlists = new HashSet<>();

    /**
     * Default constructor for Song.
     */
    public Song() {
    }

    /**
     * Constructor to initialize a Song with parameters.
     *
     * @param id    The unique identifier for the song.
     * @param title The title of the song.
     * @param genre The genre associated with the song.
     */
    public Song(Long id, String title, Genre genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    /**
     * Get the unique identifier for the song.
     *
     * @return The song's unique identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier for the song.
     *
     * @param id The song's unique identifier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the title of the song.
     *
     * @return The song's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the song.
     *
     * @param title The song's title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the genre associated with the song.
     *
     * @return The genre associated with the song.
     */
    public Genre getGenre() {
        return genre;
    }

    /**
     * Set the genre associated with the song.
     *
     * @param genre The genre associated with the song.
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }



    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre=" + genre +
                '}';
    }
}

