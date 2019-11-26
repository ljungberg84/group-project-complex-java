package se.complexjava.videostreamingapi.repository;

import org.springframework.data.repository.CrudRepository;
import se.complexjava.videostreamingapi.entity.CommentEntity;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

}
