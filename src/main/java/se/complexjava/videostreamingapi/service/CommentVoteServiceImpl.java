package se.complexjava.videostreamingapi.service;

import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.Comment;
import se.complexjava.videostreamingapi.entity.CommentVote;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceCreationException;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.CommentVoteModel;
import se.complexjava.videostreamingapi.repository.CommentRepository;
import se.complexjava.videostreamingapi.repository.CommentVoteRepository;
import se.complexjava.videostreamingapi.repository.UserRepository;

import java.util.Optional;

@Service
public class CommentVoteServiceImpl implements CommentVoteService {

    private CommentVoteRepository commentVoteRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public CommentVoteServiceImpl(CommentVoteRepository commentVoteRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.commentVoteRepository = commentVoteRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentVoteModel createCommentVote(CommentVoteModel commentVote, long userId, long commentId) throws Exception {
        Optional<User> foundUser = userRepository.findById(userId);
        Optional<Comment> foundComment = commentRepository.findById(commentId);

        if (foundUser.isPresent() && foundComment.isPresent()) {
            CommentVote commentVoteEntity = CommentVote.fromModel(commentVote);
            commentVoteEntity.setUser(foundUser.get());
            commentVoteEntity.setComment(foundComment.get());

            return CommentVoteModel.fromEntity(commentVoteRepository.save(commentVoteEntity));
        } else {
            throw new ResourceCreationException("User or comment not found");
        }
    }

    @Override
    public CommentVoteModel getCommentVote(long userId, long commentId) throws Exception {
        Optional<CommentVote> commentVote = commentVoteRepository.findByIdUserIdAndCommentId(userId, commentId);

        if(!commentVote.isPresent()){
            throw new ResourceNotFoundException(String.format("CommentVote with userId: %s and commentId %s not found", userId, commentId));
        }

        return CommentVoteModel.fromEntity(commentVote.get());
    }

    @Override
    public Iterable<CommentVoteModel> getCommentVotes() throws Exception {
        return CommentVoteModel.fromEntity(commentVoteRepository.findAll());
    }

    @Override
    public void deleteCommentVote(long userId, long commentId) throws Exception {
        commentVoteRepository.deleteByIdUserIdAndCommentId(userId, commentId);
    }

    @Override
    public CommentVoteModel updateCommentVote(CommentVoteModel commentVote, long userId, long commentId) throws Exception {
        Optional<CommentVote> optionalCommentVote = commentVoteRepository.findByIdUserIdAndCommentId(userId, commentId);

        if(!optionalCommentVote.isPresent()){
            throw new ResourceNotFoundException(String.format("CommentVote with userId: %s and commentId %s not found", userId, commentId));
        }

        CommentVote commentVoteToUpdate = optionalCommentVote.get();
        commentVoteToUpdate.setComment(commentVote.getComment());

        return CommentVoteModel.fromEntity(commentVoteRepository.save(commentVoteToUpdate));
    }
}
