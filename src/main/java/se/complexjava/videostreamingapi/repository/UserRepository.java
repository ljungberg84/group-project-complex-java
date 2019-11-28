package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.complexjava.videostreamingapi.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
