package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.repository.GenreRepository;
import com.example.musicstreamingapi.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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



    public SongService(List<Song> songsList) {
        this.songsList = songsList; // Initialize the list of songs in the constructor
    }

    public List<Song>  getAllSongs() {
        return songRepository.findAll(); // Return the list of songs
    }

    public Song createSong(Song song) { //creates a new Song object, and saves it to the database
        return songRepository.save(song);
    }

    public Song editSong(long songId, String editedSongName) {
        Optional<Song> optionalSong = songRepository.findById(songId);
        if (optionalSong.isPresent()) {
            Song existingSong = optionalSong.get();
            existingSong.setTitle(editedSongName);
            return songRepository.save(existingSong);
        } else {
            throw new IllegalArgumentException("Song not found with ID: " + songId + "not found");
        }
    }

    public Song deleteSong(long songId) {
        Song deletedSong = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("Song not found with ID: " + songId));

        songRepository.delete(deletedSong);
        return deletedSong;
    }
}
