package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.VideoView;

import java.util.List;

@Repository
public interface VideoViewRepository extends JpaRepository<VideoView, Long> {

    List<VideoView> findByVideoId(long videoId);

    List<VideoView> findByUserId(long userId);

    void deleteByVideoId(Long videoId);

    void deleteByUserId(Long userId);


}
