package com.example.musicstreamingapi.controllerTests;

import com.example.musicstreamingapi.controller.SongGenreController;
import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.service.SongService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class SGControllerTest {
    @InjectMocks
    private SongGenreController songGenreController;

    @Mock
    private SongService songServiceMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllSongsNotEmpty() {
        List<Song> songsList = Collections.singletonList(new Song());
        when(songServiceMock.getAllSongs()).thenReturn(songsList);
        ResponseEntity<?> responseEntity = songGenreController.getAllSongs();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testGetAllSongsEmpty() {
        List<Song> emptySongsList = Collections.emptyList();
        when(songServiceMock.getAllSongs()).thenReturn(emptySongsList);
        ResponseEntity<?> responseEntity = songGenreController.getAllSongs();
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
