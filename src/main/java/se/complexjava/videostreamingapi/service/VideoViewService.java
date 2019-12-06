package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.VideoViewModel;

public interface VideoViewService {

  VideoViewModel createVideoView(VideoViewModel videoViewModel) throws Exception;
  VideoViewModel getVideoView(Long videoViewId) throws Exception;
  Iterable<VideoViewModel> getVideoViews() throws Exception;
  void deleteVideoView(Long videoViewId) throws Exception;
  VideoViewModel updateVideoView(VideoViewModel videoViewModel) throws ResourceNotFoundException;
  Iterable<VideoViewModel> findVideoViewsByVideoId(Long videoId) throws ResourceNotFoundException;
  Iterable<VideoViewModel> findVideoViewsByUserId(Long userId) throws ResourceNotFoundException;
}
