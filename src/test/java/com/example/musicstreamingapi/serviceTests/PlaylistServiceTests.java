package com.example.musicstreamingapi.serviceTests;

import com.example.musicstreamingapi.model.Playlist;
import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.model.UserProfile;
import com.example.musicstreamingapi.repository.PlaylistRepository;
import com.example.musicstreamingapi.repository.UserProfileRepository;
import com.example.musicstreamingapi.security.MyUserDetails;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.doAnswer;
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
        //Creates SecurityContextHolder
        UserDetails userDetails = new MyUserDetails(testUser);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    private final UserProfile testUserProfile = new UserProfile(1L, "TestUser", "Test", "TestBio", null);
    private final User testUser = new User(1L, "TestUser", "test@test.com", "1234", testUserProfile);

    private final Playlist testPlaylist1 = new Playlist(1L, "Test Playlist",testUserProfile, new HashSet<>());
    private final Playlist testPlaylist2 = new Playlist(2L, "Test Playlist", testUserProfile, new HashSet<>());
    private final Playlist testPlaylist3 = new Playlist(3L, "Test Playlist", testUserProfile, new HashSet<>());
    private final List<Playlist> testListPlaylist = new ArrayList<>(Arrays.asList(testPlaylist1, testPlaylist2, testPlaylist3));
    private final Song testSong = new Song(1L,"Bohemian Rhapsody", null);


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

    @Test
    @DisplayName("Returns a Playlist when createPlaylist is called")
    public void testCreatePlaylist(){
        when(playlistRepositoryMock.findByNameAndUserProfile(Mockito.anyString(), Mockito.any(UserProfile.class))).thenReturn(null);
        when(playlistRepositoryMock.save(Mockito.any(Playlist.class))).thenReturn(testPlaylist1);
        Playlist result = playlistService.createPlaylist(testPlaylist1);
        Assert.assertSame(testPlaylist1, result);
    }
    @Test(expected = RuntimeException.class)
    @DisplayName("When playlist already found in database InformationExistException is thrown")
    public void testCreatePlaylistExceptionThrow(){
        when(playlistRepositoryMock.findByNameAndUserProfile(testPlaylist1.getName(), Mockito.any(UserProfile.class))).thenReturn(testPlaylist1);
        playlistService.createPlaylist(testPlaylist1);
    }

    @Test
    @DisplayName("Returns deleted Playlist when deletePlaylist is called")
    public void testDeletePlaylist(){
        when(playlistRepositoryMock.findByIdAndUserProfile(Mockito.anyLong(), Mockito.any(UserProfile.class))).thenReturn(testPlaylist1);

        doAnswer(invocationOnMock -> {
            Assert.assertSame(testPlaylist1, invocationOnMock.getArgument(0));
            return null;
        }).when(playlistRepositoryMock).delete(Mockito.any(Playlist.class));

        Playlist result = playlistService.deletePlaylist(1L);
        Assert.assertSame(testPlaylist1, result);
    }
    @Test(expected = RuntimeException.class)
    @DisplayName("When playlist not found in database InformationNotFoundException is thrown")
    public void testDeletePlaylistExceptionThrow(){
        when(playlistRepositoryMock.findByIdAndUserProfile(Mockito.anyLong(), Mockito.any(UserProfile.class))).thenReturn(null);

        playlistService.deletePlaylist(1L);

    }
    @Test
    @DisplayName("When updating playlist updated playlist is returned")
    public void testUpdatePlaylist(){
        when(playlistRepositoryMock.findByIdAndUserProfile(Mockito.anyLong(), Mockito.any(UserProfile.class))).thenReturn(testPlaylist1);
        when(playlistRepositoryMock.save(Mockito.any(Playlist.class))).thenReturn(testPlaylist2);
        Playlist result = playlistService.updatePlaylist(1L, testPlaylist2);
        Assert.assertSame(testPlaylist2, result);
    }

    @Test
    @DisplayName("Returns a list of songs when getAllSongsInPlaylist is called")
    public void testGetAllSongsInPlaylist(){
        testPlaylist1.addSong(testSong);
        when(playlistRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(testPlaylist1));

        List<Song> result= playlistService.getAllSongsInPlaylist(1L);
        Assert.assertSame(testSong, result.get(0));
    }


}
