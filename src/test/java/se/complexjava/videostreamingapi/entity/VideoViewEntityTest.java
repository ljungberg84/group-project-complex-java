package se.complexjava.videostreamingapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.composite_key.VideoViewKey;
import se.complexjava.videostreamingapi.model.VideoViewModel;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class VideoViewEntityTest {

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
  public void fromModelTest(){
    videoViewModel1 = VideoViewModel.fromEntity(videoView1);
    VideoView videoViewFromModel = VideoView.fromModel(videoViewModel1);
    assertEquals(videoView1, videoViewFromModel);
  }

  @Test
  public void fromModelsTest(){
    videoViewModel1 = VideoViewModel.fromEntity(videoView1);
    videoViewModel2 = VideoViewModel.fromEntity(videoView2);
    List<VideoViewModel> models = new ArrayList<>();
    models.add(videoViewModel1);
    models.add(videoViewModel2);
    List<VideoView> videoViews = VideoView.fromModels(models);
    assertEquals(videoViews, VideoView.fromModels(models));
  }
}
