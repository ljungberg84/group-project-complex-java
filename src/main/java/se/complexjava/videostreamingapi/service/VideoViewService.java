package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.VideoViewModel;

public interface VideoViewService {

  VideoViewModel createVideoView(Long userId, Long videoId, VideoViewModel videoViewModel) throws Exception;

  Iterable<VideoViewModel> getVideoViews() throws Exception;

  void deleteVideoViewByVideoId(Long videoId) throws Exception;
  void deleteVideoViewByUserId(Long userId) throws Exception;

  VideoViewModel updateVideoView(VideoViewModel videoViewModel, Long videoViewId) throws ResourceNotFoundException;

  Iterable<VideoViewModel> findVideoViewsByVideoId(Long videoId) throws ResourceNotFoundException;
  Iterable<VideoViewModel> findVideoViewsByUserId(Long userId) throws ResourceNotFoundException;
}
