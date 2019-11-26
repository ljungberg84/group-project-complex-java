package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.model.UserModel;
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

        UserModel userModel = userService.createUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUser (@PathVariable Long userId) throws Exception {

        UserModel userModel = userService.getUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userModel);
    }


    @GetMapping
    public ResponseEntity<List<UserModel>> getUsers () {

        List<UserModel> users = userService.getUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId){

        userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserEntity user) throws Exception{

        UserModel updatedUser = userService.updateUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }
}
