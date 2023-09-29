package com.example.musicstreamingapi.repository;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    Playlist findByNameAndUserProfile(String name, UserProfile userProfile);
    Playlist findByIdAndUserProfile(Long id, UserProfile userProfile);
    List<Playlist> findAllByUserProfileId(Long userProfile_id);
}
