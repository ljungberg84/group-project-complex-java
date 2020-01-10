package se.complexjava.videostreamingapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.complexjava.videostreamingapi.model.CommentVoteModel;
import se.complexjava.videostreamingapi.service.CommentVoteService;

import javax.validation.Valid;

@RestController
@RequestMapping("/commentVotes")
public class CommentVoteController {

    private CommentVoteService commentVoteService;

    public CommentVoteController(CommentVoteService commentVoteService) {
        this.commentVoteService = commentVoteService;
    }

    @PostMapping("/user/{userId}/comment/{commentId}")
    public ResponseEntity<CommentVoteModel> createCommentVote (@Valid @RequestBody CommentVoteModel commentVote,
                                                               @PathVariable long userId, @PathVariable long commentId) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentVoteService.createCommentVote(commentVote, userId, commentId));
    }

    @GetMapping("/user/{userId}/comment/{commentId}")
    public ResponseEntity<CommentVoteModel> getCommentVote (@PathVariable long userId, @PathVariable long commentId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(commentVoteService.getCommentVote(userId, commentId));
    }

    @GetMapping
    public ResponseEntity<Iterable<CommentVoteModel>> getCommentVotes() throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(commentVoteService.getCommentVotes());
    }

    @DeleteMapping("/user/{userId}/comment/{commentId}")
    public ResponseEntity deleteCommentVote(@PathVariable long userId, @PathVariable long commentId) throws Exception {
        commentVoteService.deleteCommentVote(userId, commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/user/{userId}/comment/{commentId}")
    public ResponseEntity updateCommentVote(@Valid @RequestBody CommentVoteModel commentVote, @PathVariable long userId, @PathVariable long commentId) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(commentVoteService.updateCommentVote(commentVote, userId, commentId));
    }
}
