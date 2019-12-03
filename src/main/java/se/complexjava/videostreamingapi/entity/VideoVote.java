package se.complexjava.videostreamingapi.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import se.complexjava.videostreamingapi.entity.composite_key.VideoVoteKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VideoVote implements Serializable {

    public VideoVote(User user, Video video) {
        this.user = user;
        this.video = video;
        this.id = new VideoVoteKey(user.getId(), video.getId());
    }

    @EmbeddedId
    private VideoVoteKey id;


    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("video_id")
    @JoinColumn(name = "video_id")
    Video video;

    boolean value;
}
