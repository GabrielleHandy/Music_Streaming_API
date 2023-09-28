package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.model.Genre;
import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.repository.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SongServiceTests {
    @Mock
    SongService songServiceMock;
    @Mock
    private SongRepository songRepository;

    private final Genre testGenre = new Genre(1L, "Test Genre", "Test Genre Description");
    private final Song testSong1 = new Song(1L, "Test Song 1", testGenre);
    private final Song testSong2 = new Song(2L, "Test Song 2", testGenre);
    private final Song testSong3 = new Song(3L, "Test Song 3", testGenre);
    private final List<Song> testListSongs = new ArrayList<>(Arrays.asList(testSong1, testSong2, testSong3));



}
