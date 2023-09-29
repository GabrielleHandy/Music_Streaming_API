package com.example.musicstreamingapi.controllerTests;

import com.example.musicstreamingapi.controller.SongGenreController;
import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.service.SongService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(SongGenreController.class)
public class SGControllerTest {

    @MockBean
    private SongService songService;

    @InjectMocks
    private SongGenreController songGenreController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(songGenreController).build();
    }

    Song testSong1 = new Song(1L, "Test Song 1", null);
    Song testSong2 = new Song(2L, "Test Song 2", null);
    Song testSong3 = new Song(3L, "Test Song 3", null);

    @Test
    public void getAllSongs() throws Exception {
        List<Song> songList = new ArrayList<>(Arrays.asList(testSong1, testSong2, testSong3));
        when(songService.getAllSongs()).thenReturn(songList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/songs/")
                        .with(jwt().jwt(builder -> builder.tokenValue("value").claim("data", "data1").header("Authorization", "Bearer value")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.message").value("Success"))
                .andDo(print());
    }
}
