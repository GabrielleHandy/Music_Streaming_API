package com.example.musicstreamingapi.controllerTests;

import com.example.musicstreamingapi.controller.PlaylistController;
import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.service.PlaylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PlaylistController.class)
public class PlaylistControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private PlaylistService playlistService;

}
