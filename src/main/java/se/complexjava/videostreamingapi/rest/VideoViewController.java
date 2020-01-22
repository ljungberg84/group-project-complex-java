package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.model.VideoViewModel;
import se.complexjava.videostreamingapi.service.VideoViewService;

import javax.validation.Valid;

@RestController
@RequestMapping("/videoViews")
public class VideoViewController {

  private VideoViewService videoViewService;

  public VideoViewController(VideoViewService videoViewService) {
    this.videoViewService = videoViewService;
  }


  @PostMapping("/{userId}/{videoId}")
  public ResponseEntity<VideoViewModel> createVideoView(
          @PathVariable(name = "userId") Long userId,
          @PathVariable(name = "videoId") Long videoId,
          @Valid @RequestBody VideoViewModel videoView) throws Exception {

    VideoViewModel videoViewModel = videoViewService.createVideoView(userId, videoId, videoView);
    return ResponseEntity.status(HttpStatus.CREATED).body(videoViewModel);
  }


  @GetMapping
  public ResponseEntity<Iterable<VideoViewModel>> getVideoViews() throws Exception {
    Iterable<VideoViewModel> videoViewModels = videoViewService.getVideoViews();
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModels);
  }


  @DeleteMapping("/video/{videoId}")
  public ResponseEntity deleteVideoViewsByVideoId(@PathVariable("videoId") Long videoId) throws Exception {
    videoViewService.deleteVideoViewByVideoId(videoId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }


  @DeleteMapping("/user/{userId}")
  public ResponseEntity deleteVideoViewsByUserId(@PathVariable("userId") Long userId) throws Exception {
    videoViewService.deleteVideoViewByVideoId(userId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }


  @PutMapping("/{videoViewId}")
  public ResponseEntity<VideoViewModel> getVideoViewsByVideoId(@Valid @RequestBody VideoViewModel videoView,
                                                               @PathVariable("videoViewId") Long videoViewId) throws Exception {
    VideoViewModel videoViewModel = videoViewService.updateVideoView(videoView, videoViewId);
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModel);
  }


  @GetMapping("/video/{videoId}")
  public ResponseEntity<Iterable<VideoViewModel>> findVideoViewsByVideoId(@PathVariable("videoId") Long videoId) throws Exception {
    Iterable<VideoViewModel> videoViewModels = videoViewService.findVideoViewsByVideoId(videoId);
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModels);
  }


  @GetMapping("/user/{userId}")
  public ResponseEntity<Iterable<VideoViewModel>> findVideoViewsByUserId(@PathVariable("userId") Long userId) throws Exception {
    Iterable<VideoViewModel> videoViewModels = videoViewService.findVideoViewsByUserId(userId);
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModels);
  }

}
