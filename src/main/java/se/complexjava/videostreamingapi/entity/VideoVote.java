package se.complexjava.videostreamingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.complexjava.videostreamingapi.entity.composite_key.VideoVoteKey;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoVote {

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
