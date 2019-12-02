package se.complexjava.videostreamingapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.VideoVote;
import se.complexjava.videostreamingapi.entity.composite_key.VideoVoteKey;

@Repository
public interface VideoVoteRepository extends JpaRepository<VideoVote, VideoVoteKey> {

    Iterable<VideoVote> findByVideoId(long videoId);

    Iterable<VideoVote> findByUserId(long userId);


}
