package se.complexjava.videostreamingapi.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.complexjava.videostreamingapi.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@SpringBootTest// use for testing all layers, spins up entire context inc rest layer
@DataJpaTest// for persistens layer testing
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)//so not to auto configure for h2
public class UserRepositoryTest {

    private String name = "name";
    private String lastName = "lastname";
    private String email = "test9@email.com";
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
