package se.complexjava.videostreamingapi.service;


import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;

import se.complexjava.videostreamingapi.repository.UserRepository;


import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository repository;


    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserEntity createUser(UserEntity user) throws Exception{

            user.setJoinDate(Instant.now());

            return repository.save(user);
    }


    @Override
    public UserEntity getUser(Long userId) throws Exception{

        Optional<UserEntity> user = repository.findById(userId);

        if(!user.isPresent()){
            throw new ResourceNotFoundException(String.format("User with id: %s not found", userId));
        }

        return user.get();
    }


    @Override
    public Iterable<UserEntity> getUsers() {


        return repository.findAll();
    }


    @Override
    public void deleteUser(Long userId) {

         repository.deleteById(userId);
    }


    @Override
    public UserEntity updateUser(UserEntity user, long userId) throws Exception{

        UserEntity userToUpdate = getUser(userId);

        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setAvatarImagePath(user.getAvatarImagePath());

        return repository.save(userToUpdate);
    }
}
