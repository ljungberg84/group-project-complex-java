package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.CommentVote;
import se.complexjava.videostreamingapi.entity.composite_key.CommentVoteKey;

@Repository
public interface CommentVoteRepository extends JpaRepository<CommentVote, CommentVoteKey> {

    Iterable<CommentVote> findByCommentId(long videoId);

    Iterable<CommentVote> findByUserId(long userId);
}
