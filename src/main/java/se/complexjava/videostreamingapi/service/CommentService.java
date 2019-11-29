package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.entity.Comment;
import se.complexjava.videostreamingapi.model.CommentModel;

import java.util.List;

public interface CommentService {

  CommentModel createComment(Comment comment) throws Exception;
  CommentModel getComment(Long commentId) throws Exception;
  List<CommentModel> getComments() throws Exception;
  void deleteComment(Long commentId);
  CommentModel updateComment(Comment comment);
}
