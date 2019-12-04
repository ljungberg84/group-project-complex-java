package se.complexjava.videostreamingapi.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.Comment;
import javax.validation.ConstraintViolationException;
import java.time.Instant;
import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CommentTest {

  @Autowired
  CommentRepository commentRepository;

  private Comment comment;

  @BeforeEach
  public void init(){
    comment = new Comment();
    comment.setTextBody("comment text");
    comment.setDateCreated(Instant.now());
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

}
