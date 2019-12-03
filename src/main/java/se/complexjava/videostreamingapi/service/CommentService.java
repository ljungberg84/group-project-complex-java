package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.CommentModel;

public interface CommentService {

  CommentModel createComment(CommentModel commentModel) throws Exception;
  CommentModel getComment(Long commentId) throws Exception;
  Iterable<CommentModel> getComments() throws Exception;
  void deleteComment(Long commentId) throws Exception;
  CommentModel updateComment(CommentModel commentModel) throws ResourceNotFoundException;
  Iterable<CommentModel> getCommentsByVideoId(Long videoId) throws ResourceNotFoundException;
}
