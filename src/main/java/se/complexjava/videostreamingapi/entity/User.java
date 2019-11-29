package se.complexjava.videostreamingapi.entity;

import lombok.*;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.model.UserModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
@AllArgsConstructor
public class UserEntity extends BaseEntity implements Serializable {


    @NotEmpty(message = "name can't be null or empty")
    private String name;

    @NotEmpty(message = "last name can't be null or empty")
    private String lastName;

    @Email
    @Column( unique = true )
    private String email;

    @NotEmpty(message = "personal id can't be null or empty")
    private String personalId;

    @NotEmpty(message = "password can't be null or empty")
    private String password;

    private String avatarImagePath;

    private Instant joinDate;

    private CategoryEntity categories;


    @OneToMany(mappedBy = "uploadedByUser")
    private Set<VideoEntity> uploadedVideos = new HashSet<>();

    //ManyToMany, mapped by userHistory
    //private Set<VideoEntity>videoHistory = new HashSet<>();

    //OneToMany, mapped by byUser
    //private Set<CommentEntity> comments = new HashSet<>();

    //OneToMany, mapped by byUser
    //private Set<VoteEntity> votes = new HashSet<>();


    private static ModelMapper modelMapper;


    public static UserEntity fromModel(UserModel model){

        return modelMapper.map(model, UserEntity.class);
    }

    public static List<UserEntity> fromModel(Iterable<UserModel> models){

        List<UserEntity> entities = new ArrayList<>();
        for (UserModel model : models) {
            entities.add(fromModel(model));
        }

        return entities;
    }
}