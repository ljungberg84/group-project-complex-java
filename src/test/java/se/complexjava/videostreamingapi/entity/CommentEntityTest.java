package se.complexjava.videostreamingapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.model.CommentModel;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CommentEntityTest {

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
  public void fromModelTest(){
    commentModel1 = CommentModel.fromEntity(comment1);
    Comment commentFromModel = Comment.fromModel(commentModel1);
    assertEquals(comment1, commentFromModel);
  }

  @Test
  public void fromModelsTest(){
    commentModel1 = CommentModel.fromEntity(comment1);
    commentModel2 = CommentModel.fromEntity(comment2);
    List<CommentModel> models = new ArrayList<>();
    models.add(commentModel1);
    models.add(commentModel2);
    List<Comment> comments = Comment.fromModels(models);
    assertEquals(comments, Comment.fromModels(models));
  }
}
