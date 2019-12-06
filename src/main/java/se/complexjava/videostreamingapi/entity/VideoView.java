package se.complexjava.videostreamingapi.entity;

import lombok.*;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.composite_key.VideoViewKey;
import se.complexjava.videostreamingapi.model.VideoViewModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "video_id")
    private Video video;

    @NotNull
    private Instant time;

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
