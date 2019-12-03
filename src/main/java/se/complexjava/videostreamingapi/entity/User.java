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
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    private Category categories;


    @OneToMany(mappedBy = "uploadedByUser")
    private Set<Video> uploadedVideos = new HashSet<>();

    //ManyToMany, mapped by userHistory
    //private Set<Video>videoHistory = new HashSet<>();

    //OneToMany, mapped by byUser
    //private Set<Comment> comments = new HashSet<>();

    //OneToMany, mapped by byUser
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
