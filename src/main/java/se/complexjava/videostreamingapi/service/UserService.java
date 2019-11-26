package se.complexjava.videostreamingapi.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.model.UserModel;

import java.util.List;

public interface UserService {

    UserModel createUser(UserEntity user) throws Exception;
    UserModel getUser(Long userId) throws Exception;
    List<UserModel> getUsers();
    void deleteUser(Long userId);
    UserModel updateUser(UserEntity user) throws Exception;
}
