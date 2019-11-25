package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.model.UserModel;
import se.complexjava.videostreamingapi.service.UserService;

import javax.validation.Valid;

@RestController("/users")
public class UserController {


    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserModel> createUser ( @Valid @RequestBody UserEntity user ){

        UserModel userModel = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
}
