package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.VideoView;

import java.util.List;


@Repository
public interface VideoViewRepository extends JpaRepository<VideoView, VideoRepository> {

    List<VideoView> findByVideoId(long videoId);

    List<VideoView> findByUserId(long userId);


    void deleteByVideoId(Long videoId);

    void deleteByUserId(Long userId);

    void deleteByVideoId(Long videoId);

    void deleteByUserId(Long userId);


}
