package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.exception.InformationNotFoundException;
import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.model.UserProfile;
import com.example.musicstreamingapi.repository.PlaylistRepository;
import com.example.musicstreamingapi.repository.UserProfileRepository;
import com.example.musicstreamingapi.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserProfileRepository userProfileRepository;
    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, UserProfileRepository userProfileRepository) {
        this.playlistRepository = playlistRepository;

        this.userProfileRepository = userProfileRepository;
    }

    public User getCurrentLoggedInUser(){
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return myUserDetails.getUser();
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


    public List<Playlist> getAllPlaylistsUserProfileId(Long userProfileId) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userProfileId);
        if(optionalUserProfile.isPresent()){
            return playlistRepository.findAllByUserProfileId(userProfileId);
        }
        throw new InformationNotFoundException("UserProfile with Id "+ userProfileId+ " not found");
    }


//    public Playlist createPlaylist(Playlist playlist) {
//        Optional<Playlist> optionalPlaylist = playlistRepository.findByNameAndUserProfile(playlist.getName(), )
//    }
}

