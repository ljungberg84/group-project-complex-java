package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.entity.BaseEntity;
import se.complexjava.videostreamingapi.entity.UserEntity;
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
    public ResponseEntity<UserModel> createUser ( @Valid @RequestBody UserModel user ) throws Exception{

        UserEntity userEntity = BaseEntity.fromModel(user, UserEntity.class);
        UserEntity entity = userService.createUser(userEntity);
        UserModel model = Model.fromEntity(entity, UserModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUser (@PathVariable Long userId) throws Exception {

        UserEntity userEntity = userService.getUser(userId);
        UserModel userModel = Model.fromEntity(userEntity, UserModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }


    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers () {

        Iterable<UserEntity>entities = userService.getUsers();
        List<UserModel> users = Model.fromEntity(entities, UserModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId){

        userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable long userId, @Valid @RequestBody UserModel user) throws Exception{

        UserEntity userEntity = BaseEntity.fromModel(user, UserEntity.class);
        UserEntity updatedEntity = userService.updateUser(userEntity, userId);
        UserModel updatedUser = Model.fromEntity(updatedEntity, UserModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }


    @PostMapping("/{userId}/videos")
    public ResponseEntity<VideoModel> addVideo (@PathVariable long userId, @Valid @RequestBody VideoModel video ) throws Exception{

        return null;
    }


    @GetMapping("/{userId}/videos")
    public ResponseEntity<VideoModel> getVideos ( @PathVariable long userId, @Valid @RequestBody VideoModel video ) throws Exception{

        return null;
    }
}
