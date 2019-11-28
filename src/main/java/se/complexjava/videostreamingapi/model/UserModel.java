package se.complexjava.videostreamingapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import se.complexjava.videostreamingapi.entity.BaseEntity;
import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.service.UserService;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends Model implements Serializable {


    @NotEmpty(message = "name can't be null or empty")
    private String name;

    @NotEmpty(message = "last name can't be null or empty")
    private String lastName;

    @Email(message = "email must be valid")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    @NotEmpty(message = "personal id can't be null or empty")
    private String personalId;

    private String avatarImagePath;

    private Instant joinDate;
}
