package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.repository.GenreRepository;
import com.example.musicstreamingapi.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.musicstreamingapi.model.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private SongRepository songRepository;
    private GenreRepository genreRepository; //ADD
    private final List<Song> songsList; // Define the list of songs

    @Autowired
    public void setSongRepository(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public SongService(List<Song> songsList) {
        this.songsList = songsList; // Initialize the list of songs in the constructor
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll(); // Return the list of songs
    }


    public List<Song> getAllSongsByGenreId(Long genreId) {
        return songRepository.findByGenreId(genreId);
    }
}
