package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.model.VideoModel;
import se.complexjava.videostreamingapi.service.VideoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("videos")
public class VideoController {


    private VideoService videoService;


    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }


    @PostMapping("/{userId}")
    public ResponseEntity<VideoModel> createVideo (@Valid @RequestBody VideoModel video, @PathVariable long userId ) throws Exception{

        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.createVideo(video, userId));
    }


    @GetMapping("/{videoId}")
    public ResponseEntity<VideoModel> getVideo (@PathVariable Long videoId) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(videoService.getVideo(videoId));
    }


    @GetMapping
    public ResponseEntity<Iterable<VideoModel>> getVideos () {

        return ResponseEntity.status(HttpStatus.OK).body(videoService.getVideos());
    }


    @DeleteMapping("/{videoId}")
    public ResponseEntity deleteVideo(@PathVariable Long videoId){

        videoService.deleteVideo(videoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{videoId}")
    public ResponseEntity updateVideo(@PathVariable long videoId, @Valid @RequestBody VideoModel video) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body(videoService.updateVideo(video, videoId));
    }


    @GetMapping("/users{userId}")
    public ResponseEntity<List<VideoModel>> getVideoByUserId (@PathVariable long userId) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body(videoService.getVideosByUserId(userId));
    }


    @GetMapping("/category{categoryId}")
    public ResponseEntity<List<VideoModel>> getVideoByCategoryId (@PathVariable long categoryId) throws Exception{

        return ResponseEntity.status(HttpStatus.OK).body(videoService.getVideosByCategoryId(categoryId));
    }
}
