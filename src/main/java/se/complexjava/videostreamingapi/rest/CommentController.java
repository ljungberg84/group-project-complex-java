package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.entity.CommentEntity;
import se.complexjava.videostreamingapi.model.CommentModel;
import se.complexjava.videostreamingapi.service.CommentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }


  @PostMapping
  public ResponseEntity<CommentModel> createComment(@Valid @RequestBody CommentEntity commentBody) throws Exception {
    CommentModel commentModel = commentService.createComment(commentBody);
    return ResponseEntity.status(HttpStatus.CREATED).body(commentModel);
  }


  @GetMapping("/all")
  public ResponseEntity<List<CommentModel>> getComments() throws Exception {
    List<CommentModel> commentModels = commentService.getComments();
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
  public ResponseEntity<CommentModel> getComment(@Valid @RequestBody CommentEntity commentBody) throws Exception {
    CommentModel commentModel = commentService.updateComment(commentBody);
    return ResponseEntity.status(HttpStatus.OK).body(commentModel);
  }


}
