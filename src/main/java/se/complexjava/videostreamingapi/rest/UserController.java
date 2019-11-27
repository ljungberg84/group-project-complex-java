package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.entity.VideoEntity;
import se.complexjava.videostreamingapi.model.Model;
import se.complexjava.videostreamingapi.model.UserModel;
import se.complexjava.videostreamingapi.model.VideoModel;
import se.complexjava.videostreamingapi.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserModel> createUser ( @Valid @RequestBody UserEntity user ) throws Exception{

        UserModel userModel = Model.fromEntity(userService.createUser(user), UserModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUser (@PathVariable Long userId) throws Exception {

        UserModel userModel = Model.fromEntity(userService.getUser(userId), UserModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }


    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers () {

        List<UserModel> users = Model.fromEntity(userService.getUsers(), UserModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId){

        userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable long userId, @Valid @RequestBody UserEntity user) throws Exception{

        UserModel updatedUser = Model.fromEntity(userService.updateUser(user, userId), UserModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }


    @PostMapping("/{userId}/videos")
    public ResponseEntity<VideoModel> addVideo (@PathVariable long userId, @Valid @RequestBody VideoEntity video ) throws Exception{

        return null;
    }


    @GetMapping("/{userId}/videos")
    public ResponseEntity<VideoModel> getVideos ( @PathVariable long userId, @Valid @RequestBody VideoEntity video ) throws Exception{

        return null;
    }
}
