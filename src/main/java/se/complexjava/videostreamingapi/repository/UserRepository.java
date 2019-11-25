package se.complexjava.videostreamingapi.repository;

import se.complexjava.videostreamingapi.entity.UserEntity;

import java.util.List;

public interface UserRepository {

    UserEntity saveUser(UserEntity user);
    UserEntity getUser(String userId);
    List<UserEntity> getUsers();
    void deleteUser(String userId);
    UserEntity updateUser(UserEntity user);
}
