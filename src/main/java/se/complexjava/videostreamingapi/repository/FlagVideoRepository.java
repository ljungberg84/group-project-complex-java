package se.complexjava.videostreamingapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import se.complexjava.videostreamingapi.entity.FlagVideo;

public interface FlagVideoRepository extends JpaRepository<FlagVideo, Long>{
	
	
	List<FlagVideo> findByUserId(long userId);
	List<FlagVideo> findByVideoId(long videoId);
	Optional<FlagVideo> findById(long userId, long videoId);
	
	void deleteById(long userId, long videoId);
}
