package se.complexjava.videostreamingapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.model.CommentModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    //id, user, dateCreated, votes, textBody, likedByUsers

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty(message = "text body cant'be bull or empty")
    private String textBody;

    @Column
    @NotEmpty(message = "date can't be null or empty")
    private Instant dateCreated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "video_id")
    private Video video;

    @OneToMany(mappedBy = "comments")
    private Set<User> usersLiked = new HashSet<>();




    private static ModelMapper modelMapper;


    public static Comment fromModel(CommentModel model){
        return modelMapper.map(model, Comment.class);
    }


    public static List<Comment> fromModel(Iterable<CommentModel> models){
        List<Comment> entities = new ArrayList<>();
        for (CommentModel model : models) {
            entities.add(fromModel(model));
        }
        return entities;
    }
}
