package se.complexjava.videostreamingapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.Comment;
import se.complexjava.videostreamingapi.entity.CommentVote;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.entity.Video;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CommentVoteMappingTest {

    @Autowired
    private CommentVoteRepository commentVoteRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    private User user;
    private Video video;
    private Comment comment;
    private CommentVote commentVote;

    @BeforeEach
    public void init() {
        user = new User();
        user.setFirstName("Aaa");
        user.setLastName("Bbb");
        user.setPersonalId("111");
        user.setEmail("aaa@mail.com");
        user.setPassword("aaa");

        video = new Video();
        video.setTitle("Aaa");
        video.setDescription("Bbb");

        comment = new Comment();
        comment.setTextBody("Aaa");
        comment.setDateCreated(Instant.now());

        commentVote = new CommentVote();
        commentVote.setValue(true);
    }

    @Test
    public void getCommentVotesByUserId() {
        User savedUser = userRepository.save(user);

        video.setUser(savedUser);
        Video savedVideo = videoRepository.save(video);

        comment.setUser(savedUser);
        comment.setVideo(savedVideo);
        Comment savedComment = commentRepository.save(comment);

        commentVote.setUser(savedUser);
        commentVote.setComment(savedComment);
        CommentVote savedCommentVote = commentVoteRepository.save(commentVote);

        List<CommentVote> commentVotes = commentVoteRepository.findByUserId(savedUser.getId());

        assertTrue(commentVotes.contains(savedCommentVote));
    }

    @Test
    public void getCommentVotesByCommentId() {
        User savedUser = userRepository.save(user);

        video.setUser(savedUser);
        Video savedVideo = videoRepository.save(video);

        comment.setUser(savedUser);
        comment.setVideo(savedVideo);
        Comment savedComment = commentRepository.save(comment);

        commentVote.setUser(savedUser);
        commentVote.setComment(savedComment);
        CommentVote savedCommentVote = commentVoteRepository.save(commentVote);

        List<CommentVote> commentVotes = commentVoteRepository.findByCommentId(savedComment.getId());

        assertTrue(commentVotes.contains(savedCommentVote));
    }

    @Test
    public void getCommentVoteByUserIdAndCommentId() {
        User savedUser = userRepository.save(user);

        video.setUser(savedUser);
        Video savedVideo = videoRepository.save(video);

        comment.setUser(savedUser);
        comment.setVideo(savedVideo);
        Comment savedComment = commentRepository.save(comment);

        commentVote.setUser(savedUser);
        commentVote.setComment(savedComment);
        CommentVote savedCommentVote = commentVoteRepository.save(commentVote);

        Optional<CommentVote> foundCommentVote = commentVoteRepository.findByIdUserIdAndCommentId(savedUser.getId(), savedComment.getId());

        assertEquals(savedCommentVote, foundCommentVote.get());
    }
}
