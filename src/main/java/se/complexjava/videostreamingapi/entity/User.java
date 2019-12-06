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
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "firstName can't be null or empty")
    private String firstName;

    @NotEmpty(message = "last firstName can't be null or empty")
    private String lastName;

    @Email(message = "email must be valid format")
    @Column( unique = true )
    private String email;

    @NotEmpty(message = "personal id can't be null or empty")
    private String personalId;

    @NotEmpty(message = "password can't be null or empty")
    private String password;

    private String avatarImagePath;

    private Instant joinDate;

    //private Set<Video> uploadedVideos = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<VideoView>videoHistory = new HashSet<>();


    //private Set<Comment> comments = new HashSet<>();

    //private Set<Vote> votes = new HashSet<>();


    private static ModelMapper modelMapper = new ModelMapper();


    public static User fromModel(UserModel model){

        return modelMapper.map(model, User.class);
    }


    public static List<User> fromModel(Iterable<UserModel> models){

        List<User> entities = new ArrayList<>();
        for (UserModel model : models) {
            entities.add(fromModel(model));
        }

        return entities;
    }
}
