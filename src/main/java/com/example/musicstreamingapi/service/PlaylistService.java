package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private PlaylistRepository playlistRepository;

    @Autowired
    public void setPlaylistRepository(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

}
