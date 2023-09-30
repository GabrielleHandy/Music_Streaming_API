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
        response.clear();
        Playlist createdPlayList = playlistService.createPlaylist(playlist);
        response.put("data", createdPlayList);
        response.put("message", "Successfully created playlist named " + createdPlayList.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{playlistId}/")
    public ResponseEntity<?> updatePlaylist(@PathVariable(name = "playlistId") Long playlistId, @RequestBody Playlist playlist){
        response.clear();
        Playlist updatedPlayList = playlistService.updatePlaylist(playlistId, playlist);

        response.put("data", updatedPlayList);
        response.put("message", "Successfully updated playlist");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{playlistId}/")
    public ResponseEntity<?> deletePlaylist(@PathVariable(name = "playlistId") Long playlistId){
        response.clear();
        Playlist deletedPlayList = playlistService.deletePlaylist(playlistId);

        response.put("data", deletedPlayList);
        response.put("message", "Successfully deleted playlist named " + deletedPlayList.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{playlistId}/songs")
    public ResponseEntity<?> getPlaylistSongs(@PathVariable(name = "playlistId") Long playlistId){
        response.clear();

        response.put("data", playlistService.getAllSongsInPlaylist(playlistId));
        response.put("message", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
