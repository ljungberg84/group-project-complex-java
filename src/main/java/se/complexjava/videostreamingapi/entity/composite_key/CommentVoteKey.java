package se.complexjava.videostreamingapi.entity.composite_key;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentVoteKey implements Serializable {

    Long userId;
    Long commentId;
}
