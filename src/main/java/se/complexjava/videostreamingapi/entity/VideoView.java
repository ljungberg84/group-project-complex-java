package se.complexjava.videostreamingapi.entity;

import lombok.*;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.composite_key.VideoViewKey;
import se.complexjava.videostreamingapi.model.VideoViewModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VideoView implements Serializable {

    @EmbeddedId
    VideoViewKey id = new VideoViewKey();

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("video_id")
    @JoinColumn(name = "video_id")
    Video video;

    Instant time;

    private static ModelMapper modelMapper = new ModelMapper();

    public static VideoView fromModel(VideoViewModel model){
        return modelMapper.map(model, VideoView.class);
    }

    public static List<VideoView> fromModels(Iterable<VideoViewModel> models){
        List<VideoView> entities = new ArrayList<>();
        for (VideoViewModel model : models) {
            entities.add(fromModel(model));
        }
        return entities;
    }
}
