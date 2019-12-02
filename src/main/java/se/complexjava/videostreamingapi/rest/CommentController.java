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


  @PostMapping
  public ResponseEntity<CommentModel> createComment(@Valid @RequestBody CommentModel commentJsonBody) throws Exception {
    CommentModel commentModel = commentService.createComment(commentJsonBody);
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
    //return ResponseEntity.status(HttpStatus.OK).body(commentModel);
  }


  @PostMapping("/{commentId}")
  public ResponseEntity<CommentModel> getComment(@Valid @RequestBody CommentModel commentJsonBody) throws Exception {
    CommentModel commentModel = commentService.updateComment(commentJsonBody);
    return ResponseEntity.status(HttpStatus.OK).body(commentModel);
  }

  @GetMapping("/videos/{videoId}")
  public ResponseEntity<Iterable<CommentModel>> getCommentByVideoId(@PathVariable("videoId") Long videoId) throws Exception {
    Iterable<CommentModel> commentModels = commentService.getCommentsByVideoId(videoId);
    return ResponseEntity.status(HttpStatus.OK).body(commentModels);
  }


}
