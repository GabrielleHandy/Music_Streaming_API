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

}
