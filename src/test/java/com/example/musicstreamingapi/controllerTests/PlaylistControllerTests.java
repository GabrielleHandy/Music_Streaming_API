package com.example.musicstreamingapi.controllerTests;

import com.example.musicstreamingapi.controller.PlaylistController;
import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.UserProfile;
import com.example.musicstreamingapi.service.PlaylistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@WebMvcTest(PlaylistController.class)
public class PlaylistControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private PlaylistService playlistService;
    private final UserProfile testUserProfile = new UserProfile(1L, "TestUser", "Test", "TestBio", null);
    private final Playlist testPlaylist1 = new Playlist(1L, "Test Playlist", LocalDate.now(), testUserProfile, new HashSet<>());
    private final Playlist testPlaylist2 = new Playlist(2L, "Test Playlist", LocalDate.now(), testUserProfile, new HashSet<>());
    private final Playlist testPlaylist3 = new Playlist(3L, "Test Playlist", LocalDate.now(), testUserProfile, new HashSet<>());
    private final List<Playlist> testListPlaylist = new ArrayList<>(Arrays.asList(testPlaylist1, testPlaylist2, testPlaylist3));
}
