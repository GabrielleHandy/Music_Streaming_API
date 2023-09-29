package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class SongService {
    private SongRepository songRepository;
    private final List<Song> songsList; // Define the list of songs
    @Autowired
    public void setSongRepository(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public SongService(List<Song> songsList) {
        this.songsList = songsList; // Initialize the list of songs in the constructor
    }

    public List<Song>  getAllSongs() {
        return songRepository.findAll(); // Return the list of songs
    }

    }
