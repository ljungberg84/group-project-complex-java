package se.complexjava.videostreamingapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.UserEntity;

import java.time.Instant;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String personalId;

    private String avatarImagePath;

    private Instant joinDate;


    @JsonIgnore
    private static ModelMapper modelMapper = new ModelMapper();

    public static UserModel fromEntity (UserEntity user){

        return modelMapper.map(user, UserModel.class);
    }
}
