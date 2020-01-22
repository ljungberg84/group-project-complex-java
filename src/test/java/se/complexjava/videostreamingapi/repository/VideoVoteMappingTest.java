package se.complexjava.videostreamingapi.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.entity.Video;
import se.complexjava.videostreamingapi.entity.VideoVote;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class VideoVoteMappingTest {

    private  String firstName = "firstName";
    private  String lastName = "lastName";
    private  String email = "test@email.com";
    private  String personalId = "12345678";
    private  String password = "123";

    private  String title = "title";
    private String description = "description";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoVoteRepository videoVoteRepository;

    private User user = new User();

    private Video video = new Video();


    @BeforeEach
    public void createUser(){

        user = new User();
        video = new Video();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPersonalId(personalId);
//        user.setPassword(password);

        video.setTitle(title);
        video.setDescription(description);
    }


    @Test
    public void videoVote_mapping_test(){

        User savedUser = userRepository.save(user);
        Video savedVideo = videoRepository.save(video);

        VideoVote vote = new VideoVote(savedUser, savedVideo);
        vote.setValue(true);

        VideoVote savedVote = videoVoteRepository.save(vote);

        assertEquals(vote, savedVote);
    }

    @Test
    public void videoVote_by_user_id_test(){

        User savedUser = userRepository.save(user);
        Video savedVideo = videoRepository.save(video);

        VideoVote vote = new VideoVote(savedUser, savedVideo);
        vote.setValue(true);

        VideoVote savedVote = videoVoteRepository.save(vote);

        List<VideoVote> vVList = videoVoteRepository.findByUserId(savedUser.getId());

        assertTrue(vVList.contains(savedVote));
    }
}
