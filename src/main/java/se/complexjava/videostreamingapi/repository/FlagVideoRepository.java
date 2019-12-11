package se.complexjava.videostreamingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.complexjava.videostreamingapi.entity.FlagVideo;

public interface FlagVideoRepository extends JpaRepository<FlagVideo, Long>{

	
	FlagVideo findByUserId(long userId);
	
	FlagVideo findByVideoId(long videoId);
	
	
	void deleteByUserId(long userId);

	void deleteByVideoId(long videoId);
}
