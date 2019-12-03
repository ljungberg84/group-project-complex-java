package se.complexjava.videostreamingapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.complexjava.videostreamingapi.entity.composite_key.VideoViewKey;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class VideoView implements Serializable {

    public VideoView(User user, Video video) {

        this.user = user;
        this.video = video;
        id = new VideoViewKey(user.getId(), video.getId());
    }

    @EmbeddedId
    VideoViewKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("video_id")
    @JoinColumn(name = "video_id")
    Video video;

    Instant time;
}
