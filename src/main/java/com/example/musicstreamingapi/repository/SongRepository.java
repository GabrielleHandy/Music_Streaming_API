package com.example.musicstreamingapi.repository;

import com.example.musicstreamingapi.model.Genre;
import com.example.musicstreamingapi.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}
