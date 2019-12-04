package se.complexjava.videostreamingapi.entity;

import lombok.*;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.model.CommentModel;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty(message = "text body cant'be null or empty")
    private String textBody;

    @Column
    @NotNull
    private Instant dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "video_id")
    private Video video;

    @OneToMany(mappedBy = "comment")
    private Set<CommentVote> commentVotes;

    private static ModelMapper modelMapper = new ModelMapper();

    public static Comment fromModel(CommentModel model){
        return modelMapper.map(model, Comment.class);
    }

    public static List<Comment> fromModels(Iterable<CommentModel> models){
        List<Comment> entities = new ArrayList<>();
        for (CommentModel model : models) {
            entities.add(fromModel(model));
        }
        return entities;
    }
}
