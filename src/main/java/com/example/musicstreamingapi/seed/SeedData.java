package com.example.musicstreamingapi.seed;

import com.example.musicstreamingapi.repository.GenreRepository;
import com.example.musicstreamingapi.repository.PlaylistRepository;
import com.example.musicstreamingapi.repository.SongRepository;
import com.example.musicstreamingapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;

    public SeedData(PasswordEncoder passwordEncoder, UserRepository userRepository, GenreRepository genreRepository, SongRepository songRepository, PlaylistRepository playlistRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
    }


    @Override
    public void run(String... args) throws Exception {
    }
}
