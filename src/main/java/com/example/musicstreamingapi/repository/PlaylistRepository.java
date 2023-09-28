package com.example.musicstreamingapi.repository;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    Object findByNameAndUserProfile(String name, UserProfile userProfile);
}
