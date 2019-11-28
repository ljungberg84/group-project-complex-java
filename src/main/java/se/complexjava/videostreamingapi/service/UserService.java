package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.model.UserModel;


public interface UserService {

    UserEntity createUser(UserModel user) throws Exception;
    UserEntity getUser(Long userId) throws Exception;
    Iterable<UserEntity> getUsers();
    void deleteUser(Long userId);
    UserEntity updateUser(UserModel user, long userId) throws Exception;
}
