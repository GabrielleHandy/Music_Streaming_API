package com.example.musicstreamingapi.model;

import javax.persistence.*;

@Entity
@Table(name = "genres")

public class Genre {

    @Id
    @Column
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

}
