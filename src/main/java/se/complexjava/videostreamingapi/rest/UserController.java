package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.model.UserModel;
import se.complexjava.videostreamingapi.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
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


    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUser (@PathVariable(name = "userId") Long userId) throws Exception {

        UserModel userModel = userService.getUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }
}
