package com.example.musicstreamingapi.seed;

import com.example.musicstreamingapi.model.Genre;
import com.example.musicstreamingapi.model.Song;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.repository.GenreRepository;
import com.example.musicstreamingapi.repository.PlaylistRepository;
import com.example.musicstreamingapi.repository.SongRepository;
import com.example.musicstreamingapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SeedData implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final GenreRepository genreRepository;

    private final SongRepository songRepository;

    private final PlaylistRepository playlistRepository;
    @Autowired
    public SeedData(@Lazy PasswordEncoder passwordEncoder, UserRepository userRepository, GenreRepository genreRepository, SongRepository songRepository, PlaylistRepository playlistRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("suresh");
        user.setEmailAddress("suresh@ga.com");
        user.setPassWord(passwordEncoder.encode("suresh123"));
        user.setUserProfile(null);
        userRepository.save(user);


        // Seed Genre: Rock
        Genre rockGenre = new Genre();
        rockGenre.setName("Rock");
        rockGenre.setDescription("A genre of popular music characterized by a strong rhythm and typically played with electric guitars, bass, and drums.");
        genreRepository.save(rockGenre);

        //First Song
        // Seed Song 1: "Bohemian Rhapsody" associated with Rock genre
        Song bohemianRhapsody = new Song();
        bohemianRhapsody.setTitle("Bohemian Rhapsody");
        bohemianRhapsody.setGenre(rockGenre); // Assuming 'rockGenre' is the previously seeded Rock genre
        songRepository.save(bohemianRhapsody);

        // Seed Song 2: "Hotel California" associated with Rock genre
        Song hotelCalifornia = new Song();
        hotelCalifornia.setTitle("Hotel California");
        hotelCalifornia.setGenre(rockGenre);
        songRepository.save(hotelCalifornia);

        // Seed Song 3: "Imagine" associated with Rock genre
        Song imagine = new Song();
        imagine.setTitle("Imagine");
        imagine.setGenre(rockGenre);
        songRepository.save(imagine);

        // Seed Genre: Hip Hop
        Genre hipHopGenre = new Genre();
        hipHopGenre.setName("Hip Hop");
        hipHopGenre.setDescription("A genre of music characterized by rhythmic speech and beats.");
        genreRepository.save(hipHopGenre);

        // Seed Song 4: "Lose Yourself" associated with Hip Hop genre
        Song loseYourself = new Song();
        loseYourself.setTitle("Lose Yourself");
        loseYourself.setGenre(hipHopGenre);
        songRepository.save(loseYourself);

        // Seed Song 5: "Sicko Mode" associated with Hip Hop genre
        Song sickoMode = new Song();
        sickoMode.setTitle("Sicko Mode");
        sickoMode.setGenre(hipHopGenre);
        songRepository.save(sickoMode);

        // Seed Genre: Country
        Genre countryGenre = new Genre();
        countryGenre.setName("Country");
        countryGenre.setDescription("A genre of music characterized by its roots in rural America and storytelling themes.");
        genreRepository.save(countryGenre);

        // Seed Song 6: "Jolene" associated with Country genre
        Song jolene = new Song();
        jolene.setTitle("Jolene");
        jolene.setGenre(countryGenre);
        songRepository.save(jolene);

        // Seed Song 7: "Wagon Wheel" associated with Country genre
        Song wagonWheel = new Song();
        wagonWheel.setTitle("Wagon Wheel");
        wagonWheel.setGenre(countryGenre);
        songRepository.save(wagonWheel);

        // Seed Song 8: "Take Me Home, Country Roads" associated with Country genre
        Song countryRoads = new Song();
        countryRoads.setTitle("Take Me Home, Country Roads");
        countryRoads.setGenre(countryGenre);
        songRepository.save(countryRoads);

    }


}
