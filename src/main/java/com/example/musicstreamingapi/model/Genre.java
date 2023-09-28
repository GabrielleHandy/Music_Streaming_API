package com.example.musicstreamingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "genre")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Song> songsList;

    // Default constructor
    public Genre () {

    }
    /**
     * Constructor to initialize Genre with parameters.
     *
     * @param id          The unique identifier for the genre.
     * @param name        The name of the genre.
     * @param description The description of the genre.
     */
    public Genre(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    /**
     * Get the unique identifier for the genre.
     *
     * @return The genre's unique identifier.
     */
    public Long getId() {
        return id;
    }
    /**
     * Set the unique identifier for the genre.
     *
     * @param id The genre's unique identifier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    /**
     * Set the name of the genre.
     *
     * @param name The genre's name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the description of the genre.
     *
     * @return The genre's description.
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set the list of songs associated with the genre.
     *
     * @param songsList The list of songs.
     */
    public void setSongsList(List<Song> songsList) {
        this.songsList = songsList;
    }

    public List<Song> getSongsList() {
        return songsList;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", songsList=" + songsList +
                '}';
    }
}
