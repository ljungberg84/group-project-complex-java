package se.complexjava.videostreamingapi.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements Serializable {

    private long id;

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

    private static ModelMapper modelMapper = new ModelMapper();

    static{
        modelMapper.typeMap(User.class, UserModel.class).addMappings(mp -> mp.skip( UserModel::setPassword));
    }


    public static UserModel fromEntity(User entity){

        return modelMapper.map(entity, UserModel.class);
    }

    public static List<UserModel> fromEntity(Iterable<User> entities){

        List<UserModel> models = new ArrayList<>();
        for (User entity : entities) {
            models.add(fromEntity(entity));
        }

        return models;
    }
}
