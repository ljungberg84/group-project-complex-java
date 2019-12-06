package se.complexjava.videostreamingapi.entity;

import lombok.*;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.composite_key.CommentVoteKey;
import se.complexjava.videostreamingapi.model.CommentVoteModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CommentVote implements Serializable {

    @EmbeddedId
    private CommentVoteKey id = new CommentVoteKey();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("commentId")
    @JoinColumn(name = "comment_id")
    Comment comment;

    boolean value;

    private static ModelMapper modelMapper = new ModelMapper();

    public static CommentVote fromModel(CommentVoteModel model){
        return modelMapper.map(model, CommentVote.class);
    }

    public static List<CommentVote> fromModel(Iterable<CommentVoteModel> models){
        List<CommentVote> entities = new ArrayList<>();
        for (CommentVoteModel model : models) {
            entities.add(fromModel(model));
        }
        return entities;
    }
}
