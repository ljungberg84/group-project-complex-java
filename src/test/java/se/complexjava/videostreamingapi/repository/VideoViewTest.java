package se.complexjava.videostreamingapi.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.Comment;
import se.complexjava.videostreamingapi.entity.VideoView;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class VideoViewTest {

  @Autowired
  CommentRepository videoRepository;

  private VideoView videoView;

  @BeforeEach
  public void init(){
    videoView = new VideoView();
//    videoView.se
  }

}
