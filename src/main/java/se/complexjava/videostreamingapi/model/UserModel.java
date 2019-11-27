package se.complexjava.videostreamingapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends Model implements Serializable {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String personalId;

    private String avatarImagePath;

    private Instant joinDate;
}
