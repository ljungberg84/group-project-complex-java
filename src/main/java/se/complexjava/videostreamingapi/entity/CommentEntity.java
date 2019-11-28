package se.complexjava.videostreamingapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class CommentEntity extends BaseEntity implements Serializable {

  //user, dateCreated, video, textBody, likedByUsers

  @Column
  @NotEmpty(message = "text body cant'be bull or empty")
  private String textBody;

  @Column
  private Instant dateCreated;

  //@OneToMany, mapped by UserEntity
  private UserEntity user = new UserEntity();

  //@OneToOne, mappedBy VideoEntity
  private VideoEntity video = new VideoEntity();

  @OneToMany(mappedBy = "likedByUsers")
  private Set<UserEntity> usersLiked = new HashSet<>();

}
