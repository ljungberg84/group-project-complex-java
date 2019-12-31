package se.complexjava.videostreamingapi.service;

import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.VideoView;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.VideoViewModel;
import se.complexjava.videostreamingapi.repository.VideoViewRepository;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Service
public class VideoViewServiceImpl implements VideoViewService {

  private VideoViewRepository repository;

  public VideoViewServiceImpl(VideoViewRepository repository) {
    this.repository = repository;
  }


  @Override
  public VideoViewModel createVideoView(VideoViewModel videoViewModel) {
    VideoView videoView = VideoView.fromModel(videoViewModel);
    videoView.setTime(Instant.now());
    return VideoViewModel.fromEntity(repository.save(videoView));
  }


  @Override
  public Iterable<VideoViewModel> getVideoViews() throws Exception {
    Iterable<VideoView> videoViews = repository.findAll();
    if(videoViews == null) {
      throw new ResourceNotFoundException(String.format("VideoViews not found"));
    }
    return VideoViewModel.fromEntities(videoViews);
  }


  @Override
  public void deleteVideoViewByVideoId(Long videoId) throws Exception  {
    repository.deleteByVideoId(videoId);
  }

  @Override
  public void deleteVideoViewByUserId(Long userId) throws Exception  {
    repository.deleteByUserId(userId);
  }


  @Override
  public VideoViewModel updateVideoView(VideoViewModel videoView) throws ResourceNotFoundException {
//    VideoView videoViewToUpdate = repository.findById(videoView.getId()).get();
//    if(videoViewToUpdate == null){
//      throw new ResourceNotFoundException(String.format("VideoView with id: %s not found", videoView.getId()));
//    }
//    videoViewToUpdate.setDateCreated(videoView.getDateCreated());
//    videoViewToUpdate.setTextBody(videoView.getTextBody());
//    repository.save(videoViewToUpdate);
//    return VideoViewModel.fromEntity(videoViewToUpdate);
    return null;  //
  }


  @Override
  public Iterable<VideoViewModel> findVideoViewsByVideoId(Long videoId) throws ResourceNotFoundException {
    Iterable<VideoView> videoViews = repository.findByVideoId(videoId);

    if(videoViews == null) {
      throw new ResourceNotFoundException(String.format("Not found"));
    }
    return VideoViewModel.fromEntities(videoViews);
  }

  @Override
  public Iterable<VideoViewModel> findVideoViewsByUserId(Long userId) throws ResourceNotFoundException {
    Iterable<VideoView> videoViews = repository.findByUserId(userId);

    if(videoViews == null) {
      throw new ResourceNotFoundException(String.format("Not found"));
    }
    return VideoViewModel.fromEntities(videoViews);
  }

}
