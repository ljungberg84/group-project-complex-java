package se.complexjava.videostreamingapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import se.complexjava.videostreamingapi.entity.UserEntity;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

}
