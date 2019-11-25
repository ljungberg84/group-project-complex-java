package se.complexjava.videostreamingapi.service;


import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.model.UserModel;

import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserModel createUser(UserEntity user) {

        user.setJoinDate(Instant.now());
        return null;
    }

    @Override
    public UserModel getUser(String userId) {
        return null;
    }

    @Override
    public List<UserModel> getUsers() {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserModel updateUser(UserEntity user) {
        return null;
    }
}
