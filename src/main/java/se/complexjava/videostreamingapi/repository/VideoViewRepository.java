package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.VideoView;
import se.complexjava.videostreamingapi.entity.composite_key.VideoViewKey;


@Repository
public interface VideoViewRepository extends JpaRepository<VideoView, VideoViewKey> {

    Iterable<VideoView> findByVideoId(long videoId);

    Iterable<VideoView> findByUserId(long userId);


}
