package com.example.musicstreamingapi.controller;


import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/songs")  // Adjust the mapping as needed
public class SongGenreController {

    private final SongService songService;

    @Autowired
    public SongGenreController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
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
}






//@RestController
//@RequestMapping("/api/songs")
//public class SongGenreController {
//    @GetMapping(path = "/songs/")
//    public ResponseEntity<?> getAllSongs() {
//      //placeholder method
//        return new ResponseEntity<>("Placeholder", HttpStatus.OK);
//    }
//}
