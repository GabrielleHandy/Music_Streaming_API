package com.example.musicstreamingapi.serviceTests;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.UserProfile;
import com.example.musicstreamingapi.repository.PlaylistRepository;
import com.example.musicstreamingapi.repository.UserProfileRepository;
import com.example.musicstreamingapi.service.PlaylistService;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.junit.Assert;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTests {

    PlaylistService playlistService;


    @Mock
    PlaylistRepository playlistRepositoryMock;
    @Mock
    UserProfileRepository userProfileRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        playlistService = new PlaylistService(playlistRepositoryMock,userProfileRepositoryMock);
    }

    private final UserProfile testUserProfile = new UserProfile(1l, "TestUser", "Test", "TestBio", null);
    private final Playlist testPlaylist1 = new Playlist(1L, "Test Playlist", LocalDate.now(), testUserProfile, new HashSet<>());
    private final Playlist testPlaylist2 = new Playlist(2L, "Test Playlist", LocalDate.now(), testUserProfile, new HashSet<>());
    private final Playlist testPlaylist3 = new Playlist(3L, "Test Playlist", LocalDate.now(), testUserProfile, new HashSet<>());
    private final List<Playlist> testListPlaylist = new ArrayList<>(Arrays.asList(testPlaylist1, testPlaylist2, testPlaylist3));


    @Test
    @DisplayName("Returns a List of Playlists when GetAllPlaylists is called")
    public void testGetAllPlaylists(){
        when(playlistRepositoryMock.findAll()).thenReturn(testListPlaylist);
        List<Playlist> result = playlistService.getAllPlaylists();
        Assert.assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Returns a Playlist when getPlaylistID is called")
    public void testGetPlaylistById(){
        when(playlistRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(testPlaylist1));
        Playlist result = playlistService.getPlaylistById(1L);
        Assert.assertSame(testPlaylist1, result);
    }
    @Test(expected = RuntimeException.class)
    @DisplayName("When playlist not found in database InformationNotFoundException is thrown")
    public void testGetPlaylistByIdExceptionThrow(){
        when(playlistRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        playlistService.getPlaylistById(1L);
    }

    @Test
    @DisplayName("Returns a User's Playlist when getPlaylistsByUserProfileId is called")
    public void testGetPlaylistsByUserProfileId(){
        when(userProfileRepositoryMock.findById(1L)).thenReturn(Optional.of(testUserProfile));
        when(playlistRepositoryMock.findAllByUserProfileId(Mockito.anyLong())).thenReturn(testListPlaylist);
        List<Playlist> result = playlistService.getAllPlaylistsUserProfileId(1L);
        Assert.assertEquals(3, result.size());
    }
//
//    @Test
//    @DisplayName("Returns a Playlist when createPlaylist is called")
//    void testCreatePlaylist(){
//        when(playlistRepository.save(Mockito.any(Playlist.class))).thenReturn(testPlaylist1);
//        Playlist result = playlistServiceMock.createPlaylist(new Playlist());
//        Assert.assertSame(testPlaylist1, result);
//    }
//    @Test(expected = RuntimeException.class)
//    @DisplayName("When playlist already found in database InformationExistException is thrown")
//    void testCreatePlaylistExceptionThrow(){
//        when(playlistRepository.findByNameAndUserProfile(Mockito.anyString(), Mockito.any(UserProfile.class))).thenReturn(testPlaylist1);
//        playlistServiceMock.createPlaylist(new Playlist());
//    }
//
//    @Test
//    @DisplayName("Returns deleted Playlist when deletePlaylist is called")
//    void testDeletePlaylist(){
//        when(playlistRepository.findById(1L)).thenReturn(Optional.of(testPlaylist1));
//        doAnswer(invocationOnMock -> {
//            Assert.assertSame(testPlaylist1, invocationOnMock.getArgument(0));
//            return null;
//        }).when(playlistRepository).delete(Mockito.any(Playlist.class));
//
//        Playlist result = playlistServiceMock.deletePlaylist(testPlaylist1);
//        Assert.assertSame(testPlaylist1, result);
//    }
//    @Test
//    @DisplayName("When playlist not found in database InformationNotFoundException is thrown")
//    void testDeletePlaylistExceptionThrow(){
//        when(playlistRepository.findById(1L)).thenReturn(Optional.empty());
//
//        playlistServiceMock.deletePlaylist(testPlaylist1);
//
//    }

}
