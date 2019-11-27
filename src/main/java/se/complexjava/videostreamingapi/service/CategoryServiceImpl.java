//package se.complexjava.videostreamingapi.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import se.complexjava.videostreamingapi.entity.CommentEntity;
//import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
//import se.complexjava.videostreamingapi.model.CommentModel;
//import se.complexjava.videostreamingapi.repository.CommentRepository;
//
//import java.time.Instant;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CommentServiceImpl implements CommentService {
//
//  private CommentRepository repository;
//
//  @Autowired
//  public CommentServiceImpl(CommentRepository repository) {
//    this.repository = repository;
//  }
//
//
//  @Override
//  public CommentModel createComment(CommentEntity comment) {
//    comment.setJoinDate(Instant.now());
//    CommentEntity savedComment = repository.save(comment);
//    return CommentModel.fromEntity(savedComment);
//  }
//
//
//  @Override
//  public CommentModel getComment(Long commentId) throws Exception {
//    Optional<CommentEntity> comment = repository.findById(commentId);
//    if (!comment.isPresent()) {
//      throw new ResourceNotFoundException(String.format("Comment with id: %s not found", commentId));
//    }
//    return CommentModel.fromEntity(comment.get());
//  }
//
//
//  @Override
//  public List<CommentModel> getComments() throws Exception {
//    List<CommentEntity> comments = (List<CommentEntity>) repository.findAll();
//    if(comments == null) {
//      throw new ResourceNotFoundException(String.format("Comments not found"));
//    }
//    return CommentModel.fromEntityList(comments);
//  }
//
//
//  @Override
//  public void deleteComment(Long commentId) {
//    repository.deleteById(commentId);
//    // check if exist, exceptions???
//  }
//
//
//  @Override
//  public CommentModel updateComment(CommentEntity comment) {
//    CommentEntity commentToUpdate = repository.findById(comment.getId()).get();
//    commentToUpdate = comment;
//    repository.save(commentToUpdate);
//    return CommentModel.fromEntity(commentToUpdate);
//  }
//
//}
