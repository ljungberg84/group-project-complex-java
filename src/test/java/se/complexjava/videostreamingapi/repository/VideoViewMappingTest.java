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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class VideoViewMappingTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoViewRepository videoViewRepository;

    @Autowired
    private VideoRepository videoRepository;


    private  String firstName = "firstName";
    private  String lastName = "lastname";
    private  String email = "test@email.com";
    private  String personalId = "12345678";
    private  String password = "123";

    private  String title = "title";
    private String description = "description";

    private User user;

    private Video video;


    @BeforeEach
    public void createUser(){

        user = new User();
        video = new Video();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPersonalId(personalId);
//        user.setPassword(password);

        video.setTitle(title);
        video.setDescription(description);
    }


//    @Test
//    public void videoView_mapping_test(){
//
//        User savedUser = userRepository.save(user);
//        Video savedVideo = videoRepository.save(video);
//
//        VideoView videoView = new VideoView(savedUser, savedVideo);
//        videoView.setTime(Instant.now());
//
//        VideoView savedVideoView = videoViewRepository.save(videoView);
//
//        assertEquals(savedUser, savedVideoView.getUser());
//        assertEquals(savedVideo, savedVideoView.getVideo());
//    }


    @Test
    public void videoView_mapping_test_2(){

        User savedUser = userRepository.save(user);
        Video savedVideo = videoRepository.save(video);

        VideoView videoView = new VideoView();
        videoView.setUser(savedUser);//savedUser, savedVideo);
        videoView.setVideo(savedVideo);
        videoView.setTime(Instant.now());


        VideoView savedVideoView = videoViewRepository.save(videoView);

        System.out.println("=======================: " + savedVideoView);


        assertEquals(savedUser, savedVideoView.getUser());
        assertEquals(savedVideo, savedVideoView.getVideo());
    }

//    @Test
//    public void find_videoView_by_user_id(){
//
//        User savedUser = userRepository.save(user);
//        Video savedVideo = videoRepository.save(video);
//
//        VideoView videoView = new VideoView(savedUser, savedVideo);
//        videoView.setTime(Instant.now());
//
//        VideoView savedVideoView = videoViewRepository.save(videoView);
//
//        List<VideoView> views = videoViewRepository.findByUserId(savedUser.getId());
//
//        assertTrue(views.contains(savedVideoView));
//    }


//    @Test
//    public void find_videoView_by_video_id(){
//
//        User savedUser = userRepository.save(user);
//        Video savedVideo = videoRepository.save(video);
//
//        VideoView videoView = new VideoView(savedUser, savedVideo);
//        videoView.setTime(Instant.now());
//
//        VideoView savedVideoView = videoViewRepository.save(videoView);
//
//        List<VideoView> views = videoViewRepository.findByVideoId(savedVideo.getId());
//
//        assertTrue(views.contains(savedVideoView));
//    }
}
