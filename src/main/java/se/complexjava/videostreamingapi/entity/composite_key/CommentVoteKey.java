package se.complexjava.videostreamingapi.entity.composite_key;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentVoteKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "comment_id")
    Long commentId;
}
