package se.complexjava.videostreamingapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.model.UserModel;
import se.complexjava.videostreamingapi.service.UserService;
import java.util.ArrayList;

@Component(value = "UserDS")
@Service
public class UserDS implements UserDetailsService {

  @Autowired
  private UserService userService;

  public UserDS(UserService userService) {
    this.userService = userService;
    //createAdmin();
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    try {
      String password = userService.getPasswordByEmail(email);
      if (!password.isEmpty()) {
        UserDetails userDetails = new User(email, password, new ArrayList<>());
        return userDetails;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public void createAdmin() {
    try {
      UserModel admin = new UserModel();
      admin.setFirstName("admin");
      admin.setLastName("admin");
      admin.setPassword("123");
      admin.setEmail("admin@mail.com");
      admin.setPersonalId("111");
      admin.setAvatarImagePath("img_path...");
      userService.createUser(admin);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //user = "admin@mail.com";
  //password = "123";
  //password = "1000:10344c223835670c27f1ef8ddaad6556:fb396d0fd6d564b8b6d9da12038c19e131d6fe4e90c22b954408df2c909c429e43154cdc41fe22079e14e7080595bf240ea763fb78c4e377936681776714f0f3";

}