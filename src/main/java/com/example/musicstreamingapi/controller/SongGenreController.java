package com.example.musicstreamingapi.controller;


import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/songs")
public class SongGenreController {

    private final SongService songService;

    @Autowired
    public SongGenreController(SongService songService) {
        this.songService = songService;
    }

    // Get all songs
    @GetMapping//"/api/songs"
    public ResponseEntity<?> getAllSongs() {
        List<Song> songsList = songService.getAllSongs();
        HashMap<String, Object> message = new HashMap<>();

        if (songsList.isEmpty()) {
            message.put("message", "Cannot find any songs.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "Success");
            message.put("data", songsList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    // Get songs by genre ID
    @GetMapping("/Genre/{genreId}")
    public ResponseEntity<?> getSongsByGenre(@PathVariable Long genreId) {
        List<Song> songsList = songService.getAllSongsByGenreId(genreId);
        HashMap<String, Object> message = new HashMap<>();
        if (songsList.isEmpty()) {
            message.put("message", "No songs found for the given genre ID.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "Success");
            message.put("data", songsList);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }


    // Get a song by ID
    @GetMapping("/{songId}")
    public ResponseEntity<?> getSongById(@PathVariable Long songId) {
        Optional<Song> optionalSong = songService.getSongById(songId);
        HashMap<String, Object> message = new HashMap<>();

        Song song = optionalSong.orElse(null);

        if (song == null) {
            message.put("message", "No song found for the given ID.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            message.put("message", "Success");
            message.put("data", song);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
}






