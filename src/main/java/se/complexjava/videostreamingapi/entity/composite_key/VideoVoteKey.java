package se.complexjava.videostreamingapi.entity.composite_key;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VideoVoteKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "video_id")
    Long commentId;
}
