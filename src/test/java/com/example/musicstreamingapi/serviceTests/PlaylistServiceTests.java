package com.example.musicstreamingapi.serviceTests;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.UserProfile;
import com.example.musicstreamingapi.repository.PlaylistRepository;
import com.example.musicstreamingapi.service.PlaylistService;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTests {
    @Mock
    PlaylistService playlistServiceMock;
    @Mock
    PlaylistRepository playlistRepository;
    private final UserProfile testUserProfile = new UserProfile(1l, "TestUser", "Test", "TestBio", null);
    private final Playlist testPlaylist1 = new Playlist(1L, "Test Playlist", LocalDate.now(), testUserProfile, new HashSet<>());
    private final Playlist testPlaylist2 = new Playlist(2L, "Test Playlist", LocalDate.now(), testUserProfile, new HashSet<>());
    private final Playlist testPlaylist3 = new Playlist(3L, "Test Playlist", LocalDate.now(), testUserProfile, new HashSet<>());
    private final List<Playlist> testListPlaylist = new ArrayList<>(Arrays.asList(testPlaylist1, testPlaylist2, testPlaylist3));


    @Test
    @DisplayName("Returns a List of Playlists when GetAllPlaylists is called")
    void testGetAllPlaylists(){
        when(playlistRepository.findAll()).thenReturn(testListPlaylist);
        List<Playlist> result = playlistServiceMock.getAllPlaylists();
        Assert.assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Returns a Playlist when getPlaylistID is called")
    void testGetPlaylistById(){
        when(playlistRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(testPlaylist1));
        Assert.assertTrue(playlistServiceMock.getPlaylistById(1).isPresent());
    }
    @Test(expected = RuntimeException.class)
    @DisplayName("When playlist not found in database InformationNotFoundException is thrown")
    void testGetPlaylistByIdExceptionThrow(){
        when(playlistRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        playlistServiceMock.getPlaylistById(1);
    }

    @Test
    @DisplayName("Returns a Playlist when createPlaylist is called")
    void testCreatePlaylist(){
        when(playlistRepository.save(Mockito.any(Playlist.class))).thenReturn(testPlaylist1);
        Playlist result = playlistServiceMock.createPlaylist(new Playlist());
        Assert.assertSame(testPlaylist1, result);
    }
    @Test(expected = RuntimeException.class)
    @DisplayName("When playlist already found in database InformationExistException is thrown")
    void testCreatePlaylistExceptionThrow(){
        when(playlistRepository.findByNameAndUserProfile(Mockito.anyString(), Mockito.any(UserProfile.class))).thenReturn(testPlaylist1);
        playlistServiceMock.createPlaylist(new Playlist());
    }

    @Test
    @DisplayName("Returns deleted Playlist when deletePlaylist is called")
    void testDeletePlaylist(){
        when(playlistRepository.findById(1L)).thenReturn(Optional.of(testPlaylist1));
        doAnswer(invocationOnMock -> {
            Assert.assertSame(testPlaylist1, invocationOnMock.getArgument(0));
            return null;
        }).when(playlistRepository).delete(Mockito.any(Playlist.class));

        Playlist result = playlistServiceMock.deletePlaylist(testPlaylist1);
        Assert.assertSame(testPlaylist1, result);
    }

}
