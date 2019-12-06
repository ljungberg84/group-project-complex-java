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


  @PostMapping
  public ResponseEntity<VideoViewModel> createVideoView(@Valid @RequestBody VideoViewModel videoViewJsonBody) throws Exception {
    VideoViewModel videoViewModel = videoViewService.createVideoView(videoViewJsonBody);
    return ResponseEntity.status(HttpStatus.CREATED).body(videoViewModel);
  }


  @GetMapping
  public ResponseEntity<Iterable<VideoViewModel>> getVideoViews() throws Exception {
    Iterable<VideoViewModel> videoViewModels = videoViewService.getVideoViews();
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModels);
  }


  @DeleteMapping("/video/{videoId}")
  public ResponseEntity<String> deleteVideoViewsByVideoId(@PathVariable("videoId") Long videoId) throws Exception {
    videoViewService.deleteVideoViewByVideoId(videoId);
    return ResponseEntity.status(HttpStatus.OK).body("deleted");
  }


  @DeleteMapping("/user/{userId}")
  public ResponseEntity<String> deleteVideoViewsByUserId(@PathVariable("userId") Long userId) throws Exception {
    videoViewService.deleteVideoViewByVideoId(userId);
    return ResponseEntity.status(HttpStatus.OK).body("deleted");
  }


  @PostMapping("/{videoViewId}")
  public ResponseEntity<VideoViewModel> getVideoViewsByVideoId(@Valid @RequestBody VideoViewModel videoViewJsonBody) throws Exception {
    VideoViewModel videoViewModel = videoViewService.updateVideoView(videoViewJsonBody);
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
