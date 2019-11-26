package se.complexjava.videostreamingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.CommentEntity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {

  private long id;
  private String user;
  private String dateCreated;
  private String textBody;
  private String likedByUser;
  private Instant joinDate;


  @JsonIgnore
  private static ModelMapper modelMapper = new ModelMapper();

  public static CommentModel fromEntity (CommentEntity comment){
    return modelMapper.map(comment, CommentModel.class);
  }


  public static List<CommentModel> fromEntityList (List<CommentEntity> comments){
    List<CommentModel> commentModels = new ArrayList<>();
    for(CommentEntity comment : comments){
      commentModels.add(modelMapper.map(comment, CommentModel.class));
    }
    return commentModels;
  }

}
