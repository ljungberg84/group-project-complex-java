package se.complexjava.videostreamingapi.service;

import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.Comment;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.entity.Video;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.CommentModel;
import se.complexjava.videostreamingapi.repository.CommentRepository;
import se.complexjava.videostreamingapi.repository.UserRepository;
import se.complexjava.videostreamingapi.repository.VideoRepository;

import java.time.Instant;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private UserRepository userRepository;
    private VideoRepository videoRepository;
    private CommentRepository commentRepository;

    public CommentServiceImpl(UserRepository userRepository, VideoRepository videoRepository,
                              CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.commentRepository = commentRepository;
    }


    @Override
    public CommentModel createComment(Long userId, Long videoId, CommentModel commentModel) {
        Comment comment = Comment.fromModel(commentModel);
        comment.setDateCreated(Instant.now());

        User user = userRepository.getOne(userId);
        comment.setUser(user);

        Video video = videoRepository.getOne(videoId);
        comment.setVideo(video);

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
        if (comments == null) {
            throw new ResourceNotFoundException(String.format("Comments not found"));
        }
        return CommentModel.fromEntities(comments);
    }


    @Override
    public void deleteComment(Long commentId) throws Exception {
        commentRepository.deleteById(commentId);
    }


    @Override
    public CommentModel updateComment(CommentModel comment, Long commentId) throws ResourceNotFoundException {
        Comment commentToUpdate = commentRepository.findById(commentId).get();
        if (commentToUpdate == null) {
            throw new ResourceNotFoundException(String.format("Comment with id: %s not found", comment.getId()));
        }
        commentToUpdate.setDateCreated(comment.getDateCreated());
        commentToUpdate.setTextBody(comment.getTextBody());
        commentRepository.save(commentToUpdate);
        return CommentModel.fromEntity(commentToUpdate);
    }


    @Override
    public Iterable<CommentModel> findCommentsByVideoId(Long videoId) throws ResourceNotFoundException {
        Iterable<Comment> comments = commentRepository.findByVideoId(videoId);

        if (comments == null) {
            throw new ResourceNotFoundException(String.format("Not found"));
        }
        return CommentModel.fromEntities(comments);
    }

    @Override
    public Iterable<CommentModel> findCommentsByUserId(Long userId) throws ResourceNotFoundException {
        Iterable<Comment> comments = commentRepository.findByUserId(userId);

        if (comments == null) {
            throw new ResourceNotFoundException(String.format("Not found"));
        }
        return CommentModel.fromEntities(comments);
    }

}
