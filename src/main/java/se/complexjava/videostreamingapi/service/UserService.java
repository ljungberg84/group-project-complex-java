package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.entity.UserEntity;


public interface UserService {

    UserEntity createUser(UserEntity user) throws Exception;
    UserEntity getUser(Long userId) throws Exception;
    Iterable<UserEntity> getUsers();
    void deleteUser(Long userId);
    UserEntity updateUser(UserEntity user, long userId) throws Exception;
}
