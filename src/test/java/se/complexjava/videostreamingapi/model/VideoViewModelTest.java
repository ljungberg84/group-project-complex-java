package se.complexjava.videostreamingapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.VideoView;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class VideoViewModelTest {

  private VideoView videoView1;
  private VideoView videoView2;
  private VideoViewModel videoViewModel1;
  private VideoViewModel videoViewModel2;

  @BeforeEach
  public void init(){
    videoView1 = new VideoView();
    videoView1.setTime(Instant.now());

    videoView2 = new VideoView();
    videoView2.setTime(Instant.now());
  }


  @Test
  public void fromEntityTest(){
    videoViewModel1 = VideoViewModel.fromEntity(videoView1);
    assertEquals(videoView1.getTime(), videoViewModel1.getTime());
    assertEquals(videoView1.getId(), videoViewModel1.getId());
  }

  @Test
  public void fromEntitiesTest(){
    videoViewModel1 = VideoViewModel.fromEntity(videoView1);
    videoViewModel2 = VideoViewModel.fromEntity(videoView2);
    List<VideoViewModel> models = new ArrayList<>();
    models.add(videoViewModel1);
    models.add(videoViewModel2);

    List<VideoView> videoViews = new ArrayList<>();
    videoViews.add(videoView1);
    videoViews.add(videoView2);

    assertEquals(models, VideoViewModel.fromEntities(videoViews));
  }

}
