package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.exception.InformationNotFoundException;
import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }



    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }


    public Playlist getPlaylistById(Long playlistId) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistId);
        if(playlistOptional.isPresent()){
            return playlistOptional.get();
        }
        throw new InformationNotFoundException("Playlist with Id " + playlistId + " not found");
    }


}
