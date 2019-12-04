package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
