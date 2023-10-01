package com.example.musicstreamingapi.controller;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.security.JWTUtils;
import com.example.musicstreamingapi.security.JwtRequestFilter;
import com.example.musicstreamingapi.security.MyUserDetailsService;
import com.example.musicstreamingapi.security.SecurityConfiguration;
import com.example.musicstreamingapi.service.PlaylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get all playlists", description = "Retrieve a list of all playlists.")
    @GetMapping("/")
    public ResponseEntity<?> getAllPlaylists(){
        List<Playlist> playListList = playlistService.getAllPlaylists();
        response.put("data", playListList );
        response.put("message", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Create a playlist", description = "Create a new playlist.")
    @ApiResponse(responseCode = "201", description = "Successfully created playlist")
    @PostMapping("/")
    public ResponseEntity<?> createPlaylist(@RequestBody Playlist playlist){

        response.clear();
        Playlist createdPlayList = playlistService.createPlaylist(playlist);
        response.put("data", createdPlayList);
        response.put("message", "Successfully created playlist named " + createdPlayList.getName());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @Operation(summary = "Update a playlist by ID", description = "Update an existing playlist by ID.")
    @PutMapping("/{playlistId}/")
    public ResponseEntity<?> updatePlaylist(
            @Parameter(description = "ID of the playlist to be updated.", required = true)
            @PathVariable(name = "playlistId") Long playlistId, @RequestBody Playlist playlist){
        response.clear();
        Playlist updatedPlayList = playlistService.updatePlaylist(playlistId, playlist);

        response.put("data", updatedPlayList);
        response.put("message", "Successfully updated playlist");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete a playlist by ID", description = "Delete an existing playlist by ID.")
    @ApiResponse(responseCode = "200", description = "Successfully deleted playlist")
    @DeleteMapping("/{playlistId}/")
    public ResponseEntity<?> deletePlaylist(
            @Parameter(description = "ID of the playlist to be deleted.", required = true)
            @PathVariable(name = "playlistId") Long playlistId){
        response.clear();
        Playlist deletedPlayList = playlistService.deletePlaylist(playlistId);

        response.put("data", deletedPlayList);
        response.put("message", "Successfully deleted playlist named " + deletedPlayList.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get all songs in a playlist by ID", description = "Retrieve all songs in a playlist by ID.")
    @GetMapping("/{playlistId}/songs/")
    public ResponseEntity<?> getPlaylistSongs(
            @Parameter(description = "ID of the playlist to retrieve songs from.", required = true)
            @PathVariable(name = "playlistId") Long playlistId){
        response.clear();

        response.put("data", playlistService.getAllSongsInPlaylist(playlistId));
        response.put("message", "Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Add a song to a playlist by ID", description = "Add a song to a playlist by specifying both playlist and song IDs.")
    @ApiResponse(responseCode = "200", description = "Song successfully added")
    @PostMapping("/{playlistId}/songs/{songId}/")
    public ResponseEntity<?> addSongToPlaylist(
            @Parameter(description = "ID of the playlist to add the song to.", required = true)
            @PathVariable(name = "playlistId") Long playlistId,
            @Parameter(description = "ID of the song to be added to the playlist.", required = true)
            @PathVariable(name = "songId") Long songId){
        response.clear();
        Playlist updatedPlaylist = playlistService.addSongToPlaylist(playlistId,songId);
        response.put("data", updatedPlaylist);
        response.put("message", "Song successfully added");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Remove a song from a playlist by ID", description = "Remove a song from a playlist by specifying both playlist and song IDs.")
    @ApiResponse(responseCode = "200", description = "Song removed from the playlist")
    @DeleteMapping("/{playlistId}/songs/{songId}/")
    public ResponseEntity<?> removeSongFromPlaylist(
            @Parameter(description = "ID of the playlist to add the song to.", required = true)
            @PathVariable(name = "playlistId") Long playlistId,
            @Parameter(description = "ID of the song to be added to the playlist.", required = true)
            @PathVariable(name = "songId") Long songId){
        response.clear();
        Playlist updatedPlaylist = playlistService.removeSongFromPlaylist(playlistId,songId);
        response.put("data", updatedPlaylist);
        response.put("message", "Song removed from the playlist");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
