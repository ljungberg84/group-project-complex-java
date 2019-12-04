package se.complexjava.videostreamingapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.VideoVote;

import java.util.List;

@Repository
public interface VideoVoteRepository extends JpaRepository<VideoVote, Long> {

    List<VideoVote> findByVideoId(long videoId);

    List<VideoVote> findByUserId(long userId);


}
