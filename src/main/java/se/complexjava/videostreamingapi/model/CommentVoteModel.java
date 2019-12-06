package se.complexjava.videostreamingapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.Comment;
import se.complexjava.videostreamingapi.entity.CommentVote;
import se.complexjava.videostreamingapi.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentVoteModel {

    private long id;
    User user;
    Comment comment;
    boolean value;

    private static ModelMapper modelMapper = new ModelMapper();

    public static CommentVoteModel fromEntity(CommentVote entity){
        return modelMapper.map(entity, CommentVoteModel.class);
    }

    public static List<CommentVoteModel> fromEntity(Iterable<CommentVote> entities){

        List<CommentVoteModel> models = new ArrayList<>();
        for (CommentVote entity : entities) {
            models.add(fromEntity(entity));
        }

        return models;
    }
}
