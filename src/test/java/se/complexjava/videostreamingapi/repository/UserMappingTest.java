package se.complexjava.videostreamingapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.entity.Video;
import se.complexjava.videostreamingapi.entity.VideoView;

import java.time.Instant;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)//so not to auto configure for h2
public class UserMappingTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    VideoViewRepository videoViewRepository;

    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "test@email.com";
    private String personalId = "12345678";
    private String password = "123";

    private  String title = "title";
    private String description = "description";


    private User user;

    private Video video;




    @BeforeEach
    public void createUser(){
        Video video = new Video();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPersonalId(personalId);
        user.setPassword(password);


        video.setTitle(title);
        video.setDescription(description);

        this.user = user;
        this.video = video;

    }


//    @Test
//    public void video_view_from_user_test(){
//
//        User savedUser = userRepository.save(user);
//        Video savedVideo = videoRepository.save(video);
//
//        VideoView view = new VideoView(savedUser, savedVideo);
//        view.setTime(Instant.now());
//
//        VideoView savedVideoView = videoViewRepository.save(view);
//
//        savedUser.getVideoHistory().add(view);
//        Optional<User> optionalUser = userRepository.findById(savedUser.getId());
//
//        if(optionalUser.isPresent()){
//            User retrievedUser = optionalUser.get();
//            assertTrue(retrievedUser.getVideoHistory().contains(savedVideoView));
//        }
//    }

}
