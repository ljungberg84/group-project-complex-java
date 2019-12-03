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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)//so not to auto configure for h2
public class UserTest {


    @Autowired
    UserRepository userRepository;

    private static String firstName = "firstName";
    private static String lastName = "lastName";
    private static String email = "test@email.com";
    private static String personalId = "12345678";
    private static String password = "123";

    private static User user = new User();


    @BeforeAll
    public static void createUser(){

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPersonalId(personalId);
        user.setPassword(password);
    }


    @Test
    @Order(1)
    public void save_valid_user_test(){

        User savedUser = userRepository.save(user);

        assertEquals(user, savedUser);
    }


    @Test
    @Order(2)
    public void save_with_invalid_email_test() {

        user.setEmail("invalid.com");

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }


    @Test
    @Order(3)
    public void save_with_null_password_test() {

        user.setPassword(null);

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }



    @Test
    @Order(4)
    public void save_with_null_firstName_test(){

        user.setFirstName(null);

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }


    @Test
    @Order(5)
    public void save_with_null_lastName_test(){

        user.setLastName(null);

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }


    @Test
    @Order(6)
    public void save_with_null_personalId_test(){

        user.setPersonalId(null);

        assertThrows(ConstraintViolationException.class, () -> userRepository.save(user));
    }
}
