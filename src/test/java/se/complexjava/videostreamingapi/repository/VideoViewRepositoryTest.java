package se.complexjava.videostreamingapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.entity.Video;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.*;
import se.complexjava.videostreamingapi.entity.VideoView;
import se.complexjava.videostreamingapi.entity.composite_key.VideoViewKey;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class VideoViewRepositoryTest {

  @Autowired
  VideoViewRepository videoViewRepository;
  @Autowired
  VideoRepository videoRepository;
  @Autowired
  UserRepository userRepository;

  private VideoView videoView;
  private Video video;
  private User user;


  @BeforeEach
  public void init(){
    videoView = new VideoView();
    videoView.setTime(Instant.now());

    video = new Video();
    video.setTitle("My video title");
    video.setDescription("My video description");

    user = new User();
    user.setFirstName("Bart");
    user.setLastName("Simpson");
    user.setEmail("bart.simpson@mail.com");
    user.setAvatarImagePath("url to my image");
    user.setPersonalId("123");
    user.setPassword("pass");
  }



  @Test
  public void saveWithValidDataTest(){
    VideoView savedVideoView = videoViewRepository.save(videoView);
    assertEquals(videoView, savedVideoView);
  }

  @Test
  public void saveWithNulITimeTest() {
    videoView.setTime(null);
    assertThrows(ConstraintViolationException.class, () -> videoViewRepository.save(videoView));
  }

  @Test
  public void videoMappingTest(){
    Video savedVideo = videoRepository.save(video);
    videoView.setVideo(savedVideo);
    VideoView savedVideoView = videoViewRepository.save(videoView);
    assertEquals(video, savedVideoView.getVideo());
  }

  @Test
  public void userMappingTest(){
    User savedUser = userRepository.save(user);
    videoView.setUser(savedUser);
    VideoView savedVideoView = videoViewRepository.save(videoView);
    assertEquals(user, savedVideoView.getUser());
  }

  @Test
  public void findVideoViewsByVideoId(){
    User savedUser = userRepository.save(user);
    videoView.setUser(savedUser);

    Video savedVideo = videoRepository.save(video);
    videoView.setVideo(savedVideo);

    VideoViewKey key = new VideoViewKey(savedUser.getId(), savedVideo.getId());
    videoView.setId(key);

    VideoView savedVideoView = videoViewRepository.save(videoView);
    List<VideoView> foundedVideoViews = videoViewRepository.findByVideoId(savedVideo.getId());

    assertTrue(foundedVideoViews.contains(savedVideoView));
  }

  @Test
  public void findVideoViewsByUserId(){
    User savedUser = userRepository.save(user);
    videoView.setUser(savedUser);

    Video savedVideo = videoRepository.save(video);
    videoView.setVideo(savedVideo);


    // funkar ej om jag inte skappar den key manuellt, ???
    //VideoViewKey key = new VideoViewKey(savedUser.getId(), savedVideo.getId());
    //videoView.setId(key);

    VideoView savedVideoView = videoViewRepository.save(videoView);

    System.out.println(savedVideoView);

    List<VideoView> foundedVideoViews = videoViewRepository.findByUserId(savedUser.getId());

    assertTrue(foundedVideoViews.contains(savedVideoView));
  }

}
