package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.repository.GenreRepository;
import com.example.musicstreamingapi.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.musicstreamingapi.model.Genre;

import java.util.List;
import java.util.Optional;
/**
 * Service class for managing songs and genres.
 */
@Service
public class SongService {
    private SongRepository songRepository;
    private GenreRepository genreRepository;
    private final List<Song> songsList;

    @Autowired
    public void setSongRepository(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    /**
     * Constructor for SongService.
     *
     * @param songsList Initial list of songs.
     */
    public SongService(List<Song> songsList) {
        this.songsList = songsList;
    }
    /**
     * Returns a list of all songs.
     *
     * @return List of all songs.
     */
    public List<Song> getAllSongs() {
        return songRepository.findAll(); // Return the list of songs
    }

    /**
     * Returns a list of songs for a given genre ID.
     *
     * @param genreId ID of the genre.
     * @return List of songs for the specified genre.
     */
    public List<Song> getAllSongsByGenreId(Long genreId) {
        return songRepository.findByGenreId(genreId);
    }
    public Optional<Song> getSongById(Long songId) {
        return songRepository.findById(songId);
    }
}
