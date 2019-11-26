package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.model.UserModel;

import java.util.List;

public interface UserService {

    UserModel createUser(UserEntity user);
    UserModel getUser(Long userId) throws Exception;
    List<UserModel> getUsers();
    void deleteUser(String userId);
    UserModel updateUser(UserEntity user);
}
