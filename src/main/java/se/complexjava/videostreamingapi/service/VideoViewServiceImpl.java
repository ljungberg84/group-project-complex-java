package se.complexjava.videostreamingapi.service;

import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.entity.Video;
import se.complexjava.videostreamingapi.entity.VideoView;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.VideoViewModel;
import se.complexjava.videostreamingapi.repository.UserRepository;
import se.complexjava.videostreamingapi.repository.VideoRepository;
import se.complexjava.videostreamingapi.repository.VideoViewRepository;
import java.time.Instant;

@Service
public class VideoViewServiceImpl implements VideoViewService {

  private UserRepository userRepository;
  private VideoRepository videoRepository;
  private VideoViewRepository videoViewRepository;

  public VideoViewServiceImpl(UserRepository userRepository, VideoRepository videoRepository,
                              VideoViewRepository videoViewRepository) {
    this.userRepository = userRepository;
    this.videoRepository = videoRepository;
    this.videoViewRepository = videoViewRepository;
  }


  @Override
  public VideoViewModel createVideoView(Long userId, Long videoId, VideoViewModel videoViewModel) {
    VideoView videoView = VideoView.fromModel(videoViewModel);
    videoView.setTime(Instant.now());

    User user = userRepository.getOne(userId);
    Video video = videoRepository.getOne(videoId);

    videoView.setUser(user);
    videoView.setVideo(video);

    return VideoViewModel.fromEntity(videoViewRepository.save(videoView));
  }


  @Override
  public Iterable<VideoViewModel> getVideoViews() throws Exception {
    Iterable<VideoView> videoViews = videoViewRepository.findAll();
    if(videoViews == null) {
      throw new ResourceNotFoundException(String.format("VideoViews not found"));
    }
    return VideoViewModel.fromEntities(videoViews);
  }


  @Override
  public void deleteVideoViewByVideoId(Long videoId) throws Exception  {
    videoViewRepository.deleteByVideoId(videoId);
  }


  @Override
  public void deleteVideoViewByUserId(Long userId) throws Exception  {
    videoViewRepository.deleteByUserId(userId);
  }


  @Override
  public VideoViewModel updateVideoView(VideoViewModel videoView, Long videoViewId) throws ResourceNotFoundException {
    VideoView videoViewToUpdate = videoViewRepository.findById(videoViewId).get();
    if(videoViewToUpdate == null){
      throw new ResourceNotFoundException(String.format("VideoView with id: %s not found", videoView.getId()));
    }
    videoViewToUpdate.setTime(videoView.getTime());
    videoViewRepository.save(videoViewToUpdate);
    return VideoViewModel.fromEntity(videoViewToUpdate);
  }


  @Override
  public Iterable<VideoViewModel> findVideoViewsByVideoId(Long videoId) throws ResourceNotFoundException {
    Iterable<VideoView> videoViews = videoViewRepository.findByVideoId(videoId);
    if(videoViews == null) {
      throw new ResourceNotFoundException(String.format("Not found"));
    }
    return VideoViewModel.fromEntities(videoViews);
  }


  @Override
  public Iterable<VideoViewModel> findVideoViewsByUserId(Long userId) throws ResourceNotFoundException {
    Iterable<VideoView> videoViews = videoViewRepository.findByUserId(userId);
    if(videoViews == null) {
      throw new ResourceNotFoundException(String.format("Not found"));
    }
    return VideoViewModel.fromEntities(videoViews);
  }
}
