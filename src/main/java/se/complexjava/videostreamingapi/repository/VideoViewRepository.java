package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.VideoView;
import se.complexjava.videostreamingapi.entity.composite_key.VideoViewKey;

import java.util.List;

@Repository
public interface VideoViewRepository extends JpaRepository<VideoView, Long> {

    List<VideoView> findByVideoId(Long videoId);

    List<VideoView> findByUserId(Long userId);


}
