package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.complexjava.videostreamingapi.entity.Video;

import java.util.List;


public interface VideoRepository extends JpaRepository<Video, Long> {

    public List<Video> getVideoByUserId(long userId);

    public List<Video> getVideoByCategoryId(long categoryId);

}
