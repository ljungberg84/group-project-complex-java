package se.complexjava.videostreamingapi.entity;

import lombok.*;
import se.complexjava.videostreamingapi.entity.composite_key.CommentVoteKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentVote implements Serializable {

    @EmbeddedId
    private CommentVoteKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("comment_id")
    @JoinColumn(name = "comment_id")
    Comment comment;

    boolean value;
}
