package com.example.musicstreamingapi.repository;

import com.example.musicstreamingapi.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository <Genre, Long> {

}
