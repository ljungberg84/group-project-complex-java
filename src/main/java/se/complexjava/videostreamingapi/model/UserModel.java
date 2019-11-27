package se.complexjava.videostreamingapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends Model implements Serializable {

    private Long id;

    @NotEmpty(message = "name can't be null or empty")
    private String name;

    @NotEmpty(message = "last name can't be null or empty")
    private String lastName;

    @Email(message = "email must be valid")
    private String email;

    private String password;

    @NotEmpty(message = "personal id can't be null or empty")
    private String personalId;

    private String avatarImagePath;

    private Instant joinDate;
}
