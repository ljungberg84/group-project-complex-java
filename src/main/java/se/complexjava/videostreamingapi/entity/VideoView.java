package se.complexjava.videostreamingapi.entity;

import lombok.*;
import se.complexjava.videostreamingapi.entity.composite_key.VideoViewKey;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VideoView implements Serializable {

    @EmbeddedId
    private VideoViewKey id = new VideoViewKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("videoId")
    @JoinColumn(name = "video_id")
    private Video video;

    private Instant time;
}
