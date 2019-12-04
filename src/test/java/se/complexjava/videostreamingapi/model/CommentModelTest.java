package se.complexjava.videostreamingapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.Comment;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CommentModelTest {

  private Comment comment1;
  private Comment comment2;
  private CommentModel commentModel1;
  private CommentModel commentModel2;

  @BeforeEach
  public void init(){
    comment1 = new Comment();
    comment1.setTextBody("My comment text 1");
    comment1.setDateCreated(Instant.now());

    comment2 = new Comment();
    comment2.setTextBody("My comment text 2");
    comment2.setDateCreated(Instant.now());
  }


  @Test
  public void fromEntityTest(){
    commentModel1 = CommentModel.fromEntity(comment1);
    assertEquals(comment1.getTextBody(), commentModel1.getTextBody());
    assertEquals(comment1.getDateCreated(), commentModel1.getDateCreated());
  }

  @Test
  public void fromEntitiesTest(){
    commentModel1 = CommentModel.fromEntity(comment1);
    commentModel2 = CommentModel.fromEntity(comment2);

    List<Comment> comments = new ArrayList<>();
    comments.add(comment1);
    comments.add(comment2);

    List<CommentModel> models = new ArrayList<>();
    models.add(commentModel1);
    models.add(commentModel2);

    assertEquals(models, CommentModel.fromEntities(comments));
  }

}
