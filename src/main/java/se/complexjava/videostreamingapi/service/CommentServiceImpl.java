package se.complexjava.videostreamingapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.Comment;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.CommentModel;
import se.complexjava.videostreamingapi.repository.CommentRepository;
import se.complexjava.videostreamingapi.repository.UserRepository;

import java.time.Instant;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  private CommentRepository commentRepository;
  private UserRepository userRepository;

  @Autowired
  public CommentServiceImpl(CommentRepository repository) {
    this.commentRepository = repository;
  }


  @Override
  public CommentModel createComment(CommentModel commentModel) {
    Comment comment = Comment.fromModel(commentModel);
    comment.setDateCreated(Instant.now());
    return CommentModel.fromEntity(commentRepository.save(comment));
  }


  @Override
  public CommentModel getComment(Long commentId) throws Exception {
    Optional<Comment> comment = commentRepository.findById(commentId);
    if (!comment.isPresent()) {
      throw new ResourceNotFoundException(String.format("Comment with id: %s not found", commentId));
    }
    return CommentModel.fromEntity(comment.get());
  }


  @Override
  public Iterable<CommentModel> getComments() throws Exception {
    Iterable<Comment> comments = commentRepository.findAll();
    if(comments == null) {
      throw new ResourceNotFoundException(String.format("Comments not found"));
    }
    return CommentModel.fromEntity(comments);
  }


  @Override
  public void deleteComment(Long commentId) throws Exception  {
    commentRepository.deleteById(commentId);
  }


  @Override
  public CommentModel updateComment(CommentModel comment) throws ResourceNotFoundException {
    Comment commentToUpdate = commentRepository.findById(comment.getId()).get();
    if(commentToUpdate == null){
      throw new ResourceNotFoundException(String.format("Comment with id: %s not found", comment.getId()));
    }
    commentToUpdate.setDateCreated(comment.getDateCreated());
    commentToUpdate.setTextBody(comment.getTextBody());
    commentRepository.save(commentToUpdate);
    return CommentModel.fromEntity(commentToUpdate);
  }


  @Override
  public Iterable<CommentModel> getCommentsByVideoId(Long videoId) throws ResourceNotFoundException {
    Iterable<Comment> comments = commentRepository.findByVideoId(videoId);

    if(comments == null) {
      throw new ResourceNotFoundException(String.format("User not found"));
    }
    return CommentModel.fromEntity(comments);
  }

}
