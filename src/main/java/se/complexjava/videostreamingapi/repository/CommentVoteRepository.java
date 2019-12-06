package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.CommentVote;
import se.complexjava.videostreamingapi.entity.composite_key.CommentVoteKey;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVote, CommentVoteKey> {

    List<CommentVote> findByUserId(long userId);

    List<CommentVote> findByCommentId(long commentId);

    Optional<CommentVote> findByIdUserIdAndCommentId(long userId, long commentId);

    void deleteByIdUserIdAndCommentId(long userId, long commentId);
}
