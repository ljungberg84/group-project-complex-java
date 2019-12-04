package se.complexjava.videostreamingapi.entity;

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
import se.complexjava.videostreamingapi.model.CommentModel;
import se.complexjava.videostreamingapi.repository.CommentRepository;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CommentTest {
  private Comment comment;
  private Video video;
  private User user;
  private CommentModel commentModel;


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
    user.setAvatarImagePath("url to my im age");
    user.setPersonalId("123");
    user.setPassword("pass");

    commentModel = new CommentModel();
//    commentModel.setId(11L);
//    commentModel.setTextBody("My comment model text");
//    commentModel.setDateCreated(Instant.now());
  }



  @Test
  public void fromModelTest(){
    commentModel = commentModel.fromEntity(comment);

    comment.fromModel(commentModel);

//    comment.fromModel(commentModel);


//    Comment commentFromModel = comment.fromModel(commentModel);


    System.out.println("_______________________________________");
    System.out.println(commentModel.getTextBody());
//    System.out.println(commentFromModel.toString());
    System.out.println("_______________________________________");
//
//
//    assertEquals(comment, commentFromModel);
  }




}
