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



  // ??? vilken id
  @GetMapping("/{videoViewId}")
  public ResponseEntity<VideoViewModel> getVideoView(@PathVariable(name = "videoViewId") Long videoViewId) throws Exception {
    VideoViewModel videoViewModel = videoViewService.getVideoView(videoViewId);
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModel);
  }


  // ??? vilken id
  @DeleteMapping("/{videoViewId}")
  public ResponseEntity<String> deleteVideoView(@PathVariable(name = "videoViewId") Long videoViewId) throws Exception {
    videoViewService.deleteVideoView(videoViewId);
    return ResponseEntity.status(HttpStatus.OK).body("VideoView with id '" + videoViewId + "' was deleted");
  }


  @PostMapping("/{videoViewId}")
  public ResponseEntity<VideoViewModel> getVideoView(@Valid @RequestBody VideoViewModel videoViewJsonBody) throws Exception {
    VideoViewModel videoViewModel = videoViewService.updateVideoView(videoViewJsonBody);
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModel);
  }

  /*
  @GetMapping("/videos/{videoId}")
  public ResponseEntity<Iterable<VideoViewModel>> findVideoViewByVideoId(@PathVariable("videoId") Long videoId) throws Exception {
    Iterable<VideoViewModel> videoViewModels = videoViewService.findVideoViewsByVideoId(videoId);
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModels);
  }
  */

  // changed endpoind for findVideoViewsByVideoId => to implement findVideoViewsByUserId


  @GetMapping("/videos/video/{videoId}")
  public ResponseEntity<Iterable<VideoViewModel>> findVideoViewsByVideoId(@PathVariable("videoId") Long videoId) throws Exception {
    Iterable<VideoViewModel> videoViewModels = videoViewService.findVideoViewsByVideoId(videoId);
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModels);
  }
  @GetMapping("/videos/user/{userId}")
  public ResponseEntity<Iterable<VideoViewModel>> findVideoViewsByUserId(@PathVariable("userId") Long userId) throws Exception {
    Iterable<VideoViewModel> videoViewModels = videoViewService.findVideoViewsByUserId(userId);
    return ResponseEntity.status(HttpStatus.OK).body(videoViewModels);
  }

}
