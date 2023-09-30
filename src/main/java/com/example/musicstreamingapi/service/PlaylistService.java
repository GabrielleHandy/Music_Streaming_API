package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.exception.InformationExistException;
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


/**
 * Service class for managing playlists and related operations.
 */
@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final UserProfileRepository userProfileRepository;

    /**
     * Constructor for PlaylistService.
     *
     * @param playlistRepository     The repository for managing playlists.
     * @param userProfileRepository The repository for managing user profiles.
     */
    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, UserProfileRepository userProfileRepository) {
        this.playlistRepository = playlistRepository;

        this.userProfileRepository = userProfileRepository;
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The currently logged-in user.
     */
    public User getCurrentLoggedInUser(){

            MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return myUserDetails.getUser();


    }

    /**
     * Retrieves a list of all playlists.
     *
     * @return A list of all playlists.
     */
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }


    /**
     * Retrieves a playlist by its unique identifier.
     *
     * @param playlistId The unique identifier of the playlist.
     * @return The playlist if found, otherwise throws {@link InformationNotFoundException}.
     */
    public Playlist getPlaylistById(Long playlistId) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistId);
        if(playlistOptional.isPresent()){
            return playlistOptional.get();
        }
        throw new InformationNotFoundException("Playlist with Id " + playlistId + " not found");
    }

    /**
     * Retrieves all playlists associated with a specific user profile.
     *
     * @param userProfileId The unique identifier of the user profile.
     * @return A list of playlists associated with the user profile, or throws {@link InformationNotFoundException} if the user profile is not found.
     */
    public List<Playlist> getAllPlaylistsUserProfileId(Long userProfileId) {
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userProfileId);
        if(optionalUserProfile.isPresent()){
            return playlistRepository.findAllByUserProfileId(userProfileId);
        }
        throw new InformationNotFoundException("UserProfile with Id "+ userProfileId+ " not found");
    }

    /**
     * Creates a new playlist.
     *
     * @param playlist The playlist to create.
     * @return The created playlist if it doesn't already exist, otherwise throws {@link InformationExistException}.
     */
    public Playlist createPlaylist(Playlist playlist) {
        Optional<Playlist> optionalPlaylist = Optional.ofNullable(playlistRepository.findByNameAndUserProfile(playlist.getName(),getCurrentLoggedInUser().getUserProfile()));
        if(optionalPlaylist.isEmpty()) {
            playlist.setUserProfile(getCurrentLoggedInUser().getUserProfile());
            return playlistRepository.save(playlist);
        }
        throw new InformationExistException("Playlist with name " + playlist.getName()+ " already exists");
    }

    /**
     * Deletes a playlist by its unique identifier.
     *
     * @param playlistId The unique identifier of the playlist to delete.
     * @return The deleted playlist if found, otherwise throws {@link InformationExistException}.
     */
    public Playlist deletePlaylist(Long playlistId) {
        Playlist optionalPlaylist = playlistRepository.findByIdAndUserProfile(playlistId ,getCurrentLoggedInUser().getUserProfile());
        if(optionalPlaylist != null) {
            playlistRepository.delete(optionalPlaylist);
            return optionalPlaylist;
        }
        throw new InformationExistException("You don't have a playlist with Id " + playlistId);
    }

    public Playlist updatePlaylist(Long playlistId, Playlist updatedPlaylist) {
        Playlist optionalPlaylist = playlistRepository.findByIdAndUserProfile(playlistId ,getCurrentLoggedInUser().getUserProfile());
        if(optionalPlaylist != null) {
            optionalPlaylist.setName(updatedPlaylist.getName());
            return playlistRepository.save(optionalPlaylist);

        }
        throw new InformationExistException("You don't have a playlist with Id " + playlistId);
    }
}

