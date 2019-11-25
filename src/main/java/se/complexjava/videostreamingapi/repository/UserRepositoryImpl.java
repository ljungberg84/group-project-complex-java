package se.complexjava.videostreamingapi.repository;

import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.UserEntity;

import java.util.List;

@Repository
public class UserRepositoryImpl  implements UserRepository {

    @Override
    public UserEntity saveUser(UserEntity user) {

        
        return null;
    }

    @Override
    public UserEntity getUser(String userId) {
        return null;
    }

    @Override
    public List<UserEntity> getUsers() {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        return null;
    }
}
