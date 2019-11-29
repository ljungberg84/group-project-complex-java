package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.complexjava.videostreamingapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
