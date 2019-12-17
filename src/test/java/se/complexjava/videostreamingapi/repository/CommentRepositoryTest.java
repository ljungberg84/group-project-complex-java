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
import se.complexjava.videostreamingapi.entity.Comment;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

  @Autowired
  CommentRepository commentRepository;
  @Autowired
  VideoRepository videoRepository;
  @Autowired
  UserRepository userRepository;

  private Comment comment;
  private Video video;
  private User user;


  @BeforeEach
  public void init(){
    comment = new Comment();
    comment.setTextBody("My comment text");
    comment.setDateCreated(Instant.now());

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
    Comment savedComment = commentRepository.save(comment);
    assertEquals(comment, savedComment);
  }

  @Test
  public void saveWithNulIDateCreatedTest() {
    comment.setDateCreated(null);
    assertThrows(ConstraintViolationException.class, () -> commentRepository.save(comment));
  }

  @Test
  public void saveWithNullTextBodyTest() {
    comment.setTextBody(null);
    assertThrows(ConstraintViolationException.class, () -> commentRepository.save(comment));
  }

  @Test
  public void saveWithEmptyTextBodyTest() {
    comment.setTextBody("");
    assertThrows(ConstraintViolationException.class, () -> commentRepository.save(comment));
  }

  @Test
  public void videoMappingTest(){
    comment.setVideo(video);
    Comment savedComment = commentRepository.save(comment);
    assertEquals(video, savedComment.getVideo());
  }

  @Test
  public void userMappingTest(){
    comment.setUser(user);
    Comment savedComment = commentRepository.save(comment);
    assertEquals(user, savedComment.getUser());
  }

  @Test
  public void findCommentsByVideoId(){
    Video savedVideo = videoRepository.save(video);
    comment.setVideo(savedVideo);
    Comment savedComment = commentRepository.save(comment);
    List<Comment> foundedComments = commentRepository.findByVideoId(savedVideo.getId());
    assertTrue(foundedComments.contains(savedComment));
  }

  @Test
  public void findCommentsByUserId(){
    User savedUser = userRepository.save(user);
    comment.setUser(savedUser);
    Comment savedComment = commentRepository.save(comment);
    List<Comment> foundedComments = commentRepository.findByUserId(savedUser.getId());
    assertTrue(foundedComments.contains(savedComment));
  }

}
