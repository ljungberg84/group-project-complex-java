package se.complexjava.videostreamingapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotEmpty(message = "user can't be null or empty")
  private String user;

  @NotEmpty(message = "date created can't be null or empty")
  private String dateCreated;

  @NotEmpty(message = "text body can't be null or empry")
  private String textBody;

  @NotEmpty(message = "liked by can't be null or empty")
  private String likedByUser;

  private Instant joinDate;

}
