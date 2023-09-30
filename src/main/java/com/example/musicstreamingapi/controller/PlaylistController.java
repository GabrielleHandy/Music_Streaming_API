package com.example.musicstreamingapi.controller;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.security.JWTUtils;
import com.example.musicstreamingapi.security.JwtRequestFilter;
import com.example.musicstreamingapi.security.MyUserDetailsService;
import com.example.musicstreamingapi.security.SecurityConfiguration;
import com.example.musicstreamingapi.service.PlaylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {


    private PlaylistService playlistService;

    @Autowired
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    private HashMap<String, Object> response = new HashMap<>();

    @GetMapping("/")
    public ResponseEntity<?> getAllPlaylists(){
        List<Playlist> playListList = playlistService.getAllPlaylists();
        response.put("data", playListList );
        response.put("message", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createPlaylist(@RequestBody Playlist playlist){
        Playlist createdPlayList = playlistService.createPlaylist(playlist);
        response.put("data", createdPlayList);
        response.put("message", "Successfully created playlist named " + createdPlayList.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
