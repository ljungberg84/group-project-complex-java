package se.complexjava.videostreamingapi.service;

        import se.complexjava.videostreamingapi.model.UserModel;

public interface UserService {

    UserModel createUser(UserModel user) throws Exception;
    UserModel getUser(Long userId) throws Exception;
    Iterable<UserModel> getUsers();
    void deleteUser(Long userId);
    UserModel updateUser(UserModel user, long userId) throws Exception;
    UserModel getUserByEmail(String email) throws Exception;
}
