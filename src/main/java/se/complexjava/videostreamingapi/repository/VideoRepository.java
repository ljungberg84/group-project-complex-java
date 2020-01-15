package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.complexjava.videostreamingapi.entity.Video;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    List<Video> findByUserId(long userId);
    List<Video> findByCategoriesId(long categoryId);
    Video findByUserIdAndTitle(long userId, String title);
    Video deleteByUserIdAndTitle(long userId, String title);


}
