package se.complexjava.videostreamingapi.repository;

import org.springframework.data.repository.CrudRepository;
import se.complexjava.videostreamingapi.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
