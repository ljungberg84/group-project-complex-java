package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.complexjava.videostreamingapi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
