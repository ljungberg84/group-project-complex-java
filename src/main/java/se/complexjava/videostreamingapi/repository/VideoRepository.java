package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.complexjava.videostreamingapi.entity.Video;

import java.util.List;


public interface VideoRepository extends JpaRepository<Video, Long> {

    public List<Video> findByUserId(long userId);

    public List<Video> findByCategoryId(long categoryId);

}
