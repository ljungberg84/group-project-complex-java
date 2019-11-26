package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.entity.CommentEntity;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.CommentModel;
import java.util.List;

public interface CommentService {

  CommentModel createComment(CommentEntity comment) throws Exception;
  CommentModel getComment(Long commentId) throws Exception;
  List<CommentModel> getComments() throws Exception;
  void deleteComment(Long commentId);
  CommentModel updateComment(CommentEntity comment);
}
