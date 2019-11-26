package se.complexjava.videostreamingapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {


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

    //placeholder for VideoEntity
    //OneToMany, mapped by uploadedByUser
    //private Set<String> uploadedVideos = new HashSet<>();
    //placeholder for VideoEntity
    //ManyToMany, mapped by userHistory
    //private Set<String>videoHistory = new HashSet<>();
    //placeholder for CommentEntity
    //OneToMany, mapped by byUser
    //private Set<String> comments = new HashSet<>();
    //placeholder for  LikeEntity
    //OneToMany, mapped by byUser
    //private Set<String> likes = new HashSet<>();
}
