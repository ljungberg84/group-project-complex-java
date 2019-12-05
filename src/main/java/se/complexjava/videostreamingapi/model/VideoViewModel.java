package se.complexjava.videostreamingapi.model;

import lombok.*;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.Comment;
import se.complexjava.videostreamingapi.entity.VideoView;
import se.complexjava.videostreamingapi.entity.composite_key.VideoViewKey;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VideoViewModel implements Serializable {

  private static final long serialVersionUID = 1L;

  // ????????? ska man ha den i model, new instance???
  VideoViewKey id = new VideoViewKey();

  @NotEmpty(message = "time can't be null or empty")
  Instant time;

  private static ModelMapper modelMapper = new ModelMapper();

  public static VideoViewModel fromEntity(VideoView entity){
    return modelMapper.map(entity, VideoViewModel.class);
  }

  public static List<VideoViewModel> fromEntities(Iterable<VideoView> entities){
    List<VideoViewModel> models = new ArrayList<>();
    for (VideoView entity : entities) {
      models.add(fromEntity(entity));
    }
    return models;
  }
}
