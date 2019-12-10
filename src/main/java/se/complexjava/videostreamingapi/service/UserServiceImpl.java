package se.complexjava.videostreamingapi.service;


import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;

import se.complexjava.videostreamingapi.model.UserModel;
import se.complexjava.videostreamingapi.repository.UserRepository;
import se.complexjava.videostreamingapi.security.HashEncoder;


import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private HashEncoder hashEncoder = new HashEncoder();

  private UserRepository repository;


  public UserServiceImpl(UserRepository repository) {
    this.repository = repository;
  }


  @Override
  public UserModel createUser(UserModel user) throws Exception {
    String strongPassword = hashEncoder.encode(user.getPassword());
    user.setPassword(strongPassword);

    User userEntity = User.fromModel(user);
    user.setJoinDate(Instant.now());

    return UserModel.fromEntity(repository.save(userEntity));
  }


  @Override
  public UserModel getUser(Long userId) throws Exception {

    Optional<User> user = repository.findById(userId);

    if (!user.isPresent()) {
      throw new ResourceNotFoundException(String.format("User with id: %s not found", userId));
    }

    return UserModel.fromEntity(user.get());
  }


  @Override
  public Iterable<UserModel> getUsers() {

    return UserModel.fromEntity(repository.findAll());
  }


  @Override
  public void deleteUser(Long userId) {

    repository.deleteById(userId);
  }


  @Override
  public UserModel updateUser(UserModel user, long userId) throws Exception {

    Optional<User> optionalUser = repository.findById(userId);

    if (!optionalUser.isPresent()) {
      throw new ResourceNotFoundException(String.format("User with id: %s not found", userId));
    }

    User userToUpdate = optionalUser.get();
    userToUpdate.setFirstName(user.getFirstName());
    userToUpdate.setLastName(user.getLastName());
    userToUpdate.setEmail(user.getEmail());
    userToUpdate.setPassword(user.getPassword());
    userToUpdate.setAvatarImagePath(user.getAvatarImagePath());

    return UserModel.fromEntity(repository.save(userToUpdate));
  }


  @Override
  public String getPasswordByEmail(String email) throws Exception {
    Optional<User> user = repository.findByEmail(email);
    if (!user.isPresent()) {
      throw new ResourceNotFoundException(String.format("User with email: %s not found", email));
    }
    return user.get().getPassword();
  }

}
