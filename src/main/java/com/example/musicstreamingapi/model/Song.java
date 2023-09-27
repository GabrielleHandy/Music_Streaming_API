package com.example.musicstreamingapi.model;


import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String genre;
}
