package se.complexjava.videostreamingapi.repository;


import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.User;

import javax.validation.ConstraintViolationException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest// for persistens layer testing
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)//so not to auto configure for h2
public class UserTest {


    @Autowired
    UserRepository userRepository;

    private String firstName = "firstName";
    private String lastName = "lastName";
    private String email = "test@email.com";
    private String personalId = "12345678";
    private String password = "123";

    private User user = new User();


    @BeforeEach
    public void createUser(){
        User u = new User();
        u.setFirstName(firstName);
        u.setLastName(lastName);
        u.setEmail(email);
        u.setPersonalId(personalId);
//        u.setPassword(password);

        user = u;
    }


    @Test
    public void save_valid_user_test(){

        User savedUser = userRepository.save(user);

        assertEquals(user, savedUser);
    }


    @Test
    public void save_with_invalid_email_test() {

        user.setEmail("invalid.com");

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }


    @Test
    public void save_with_null_password_test() {

//        user.setPassword(null);

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }



    @Test
    public void save_with_null_firstName_test(){

        user.setFirstName(null);

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }


    @Test
    public void save_with_null_lastName_test(){

        user.setLastName(null);

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }


    @Test
    public void save_with_null_personalId_test(){

        user.setPersonalId(null);

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }
}
