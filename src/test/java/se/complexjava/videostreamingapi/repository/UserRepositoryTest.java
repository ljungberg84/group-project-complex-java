package se.complexjava.videostreamingapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.complexjava.videostreamingapi.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    private String name = "name";
    private String lastName = "lastname";
    private String email = "test3@email.com";
    private String personalId = "12345678";
    private String password = "123";

    @Autowired
    private UserRepository repository;


    @Test
    public void saveUserTest(){

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPersonalId(personalId);
        user.setPassword(password);

        User savedUser = repository.save(user);

        assertEquals(user.getName(), name);
    }
}
