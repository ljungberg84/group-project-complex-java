package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  Iterable<Comment> findByVideoId(Long videoId);


}
