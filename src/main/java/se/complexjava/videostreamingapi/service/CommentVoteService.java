package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.model.CommentVoteModel;

public interface CommentVoteService {
    CommentVoteModel createCommentVote(CommentVoteModel commentVote, long userId, long commentId) throws Exception;
    CommentVoteModel getCommentVote(long userId, long commentId) throws Exception;
    Iterable<CommentVoteModel> getCommentVotes() throws Exception;
    void deleteCommentVote(long userId, long commentId) throws Exception;
    CommentVoteModel updateCommentVote(CommentVoteModel commentVote, long userId, long commentId) throws Exception;
}
