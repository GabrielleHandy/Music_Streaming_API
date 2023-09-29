package com.example.musicstreamingapi.service;

import com.example.musicstreamingapi.model.Genre;
import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.repository.SongRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SongServiceTests {

    @InjectMocks
    SongService songServiceMock;
    @Mock
    private SongRepository songRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
         // Create an instance of your service with the mock repository
    }

    private final Genre testGenre = new Genre(1L, "Test Genre", "Test Genre Description");
    private final Song testSong1 = new Song(1L, "Test Song 1", testGenre);
    private final Song testSong2 = new Song(2L, "Test Song 2", testGenre);
    private final Song testSong3 = new Song(3L, "Test Song 3", testGenre);
    private final List<Song> testListSongs = new ArrayList<>(Arrays.asList(testSong1, testSong2, testSong3));


    @Test
    public void getAllSongs_success() {
        songServiceMock.setSongRepository(songRepository); // Mock the behavior of the repository
        when(songRepository.findAll()).thenReturn(testListSongs);    // Call the service method
        List<Song> songs = songServiceMock.getAllSongs();
        // Verify the result
        assertEquals(3, songs.size()); // Add more assertions based on the expected behavior

    }
    @Test
    public void testCreateSongs_success () {
        when(songRepository.save(Mockito.any(Song.class))).thenReturn(testSong1);
        Song createSong = songServiceMock.createSong(new Song());
        assertEquals(testSong1, createSong);
    }
    @Test
    public void testEditSongs_success() {

        when(songRepository.findById(1L)).thenReturn(Optional.ofNullable(testSong1)); // Mock the song to edit
        when(songRepository.save(Mockito.any(Song.class))).thenReturn(testSong1); // Mock the save operation for any Song
        Song editedSong = songServiceMock.editSong(1L, "Edited Song Name");
        assertEquals("Edited Song Name", editedSong.getTitle());
    }


    @Test
    public void testDeleteSongs_success() {
        when(songRepository.findById(2L)).thenReturn(Optional.ofNullable(testSong2));
        doAnswer(invocation -> {
            Song songToDelete = invocation.getArgument(0);
            songToDelete.setTitle("Deleted Song Name");
            return null;
        }).when(songRepository).delete(Mockito.any(Song.class));
        Song deletedSong = songServiceMock.deleteSong(2L);
        assertEquals("Deleted Song Name", deletedSong.getTitle());
    }


}

