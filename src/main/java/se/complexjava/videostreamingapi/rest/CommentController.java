package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.model.CommentModel;
import se.complexjava.videostreamingapi.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }


  @PostMapping("/{userId}/{videoId}")
  public ResponseEntity<CommentModel> createComment(
          @PathVariable(name = "userId") Long userId,
          @PathVariable(name = "videoId") Long videoId,
          @Valid @RequestBody CommentModel comment) throws Exception {

    CommentModel commentModel = commentService.createComment(userId, videoId, comment);
    return ResponseEntity.status(HttpStatus.CREATED).body(commentModel);
  }


  @GetMapping
  public ResponseEntity<Iterable<CommentModel>> getComments() throws Exception {
    Iterable<CommentModel> commentModels = commentService.getComments();
    return ResponseEntity.status(HttpStatus.OK).body(commentModels);
  }


  @GetMapping("/{commentId}")
  public ResponseEntity<CommentModel> getComment(@PathVariable(name = "commentId") Long commentId) throws Exception {
    CommentModel commentModel = commentService.getComment(commentId);
    return ResponseEntity.status(HttpStatus.OK).body(commentModel);
  }


  @DeleteMapping("/{commentId}")
  public ResponseEntity<String> deleteComment(@PathVariable(name = "commentId") Long commentId) throws Exception {
    commentService.deleteComment(commentId);
    return ResponseEntity.status(HttpStatus.OK).body("Comment with id '" + commentId + "' was deleted");
  }


  @PutMapping("/{commentId}")
  public ResponseEntity<CommentModel> updateComment(@Valid @RequestBody CommentModel comment,
                                                    @PathVariable("commentId") Long commentId) throws Exception {
    CommentModel commentModel = commentService.updateComment(comment, commentId);
    return ResponseEntity.status(HttpStatus.OK).body(commentModel);
  }


  @GetMapping("/videos/video/{videoId}")
  public ResponseEntity<Iterable<CommentModel>> findCommentsByVideoId(@PathVariable("videoId") Long videoId) throws Exception {
    Iterable<CommentModel> commentModels = commentService.findCommentsByVideoId(videoId);
    return ResponseEntity.status(HttpStatus.OK).body(commentModels);
  }


  @GetMapping("/videos/user/{userId}")
  public ResponseEntity<Iterable<CommentModel>> findCommentsByUserId(@PathVariable("userId") Long userId) throws Exception {
    Iterable<CommentModel> commentModels = commentService.findCommentsByUserId(userId);
    return ResponseEntity.status(HttpStatus.OK).body(commentModels);
  }

}
