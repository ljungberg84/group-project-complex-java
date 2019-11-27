package se.complexjava.videostreamingapi.service;


import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.UserEntity;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.Model;
import se.complexjava.videostreamingapi.model.UserModel;
import se.complexjava.videostreamingapi.repository.UserRepository;


import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository repository;


    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserModel createUser(UserEntity user) throws Exception{

            user.setJoinDate(Instant.now());
            UserEntity savedUser = repository.save(user);

            return Model.fromEntity(savedUser, UserModel.class);
    }


    @Override
    public UserModel getUser(Long userId) throws Exception{

        Optional<UserEntity> user = repository.findById(userId);

        if(!user.isPresent()){
            throw new ResourceNotFoundException(String.format("User with id: %s not found", userId));
        }

        return Model.fromEntity(user.get(), UserModel.class);
    }


    @Override
    public List<UserModel> getUsers() {

        Iterable<UserEntity> users = repository.findAll();

        return Model.fromEntity(users, UserModel.class);
    }


    @Override
    public void deleteUser(Long userId) {

         repository.deleteById(userId);
    }


    @Override
    public UserModel updateUser(UserEntity user, long userId) throws Exception{

        Optional<UserEntity> optionalUser = repository.findById(userId);

        if(!optionalUser.isPresent()){
            throw new ResourceNotFoundException(String.format("Cant update user: User with id: %s not found", user.getId()));
        }

        UserEntity userToUpdate = optionalUser.get();

        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setAvatarImagePath(user.getAvatarImagePath());

        userToUpdate = repository.save(userToUpdate);

        return Model.fromEntity(userToUpdate, UserModel.class);
    }
}
