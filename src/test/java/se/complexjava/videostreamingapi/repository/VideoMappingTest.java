package se.complexjava.videostreamingapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class VideoMappingTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    VideoVoteRepository videoVoteRepository;

    @Autowired
    CategoryRepository categoryRepository;


    private  String firstName = "firstName";
    private  String lastName = "lastname";
    private  String email = "test@email.com";
    private  String personalId = "12345678";
    private  String password = "123";

    private  String title = "title";
    private String description = "description";

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
        user.setPassword(password);

        video.setTitle(title);
        video.setDescription(description);
    }


    @Test
    public void save_video_test(){

        Video savedVideo = videoRepository.save(video);

        assertEquals(video, savedVideo);
    }


    @Test
    public void video_user_mapping_test(){

        User savedUser = userRepository.save(user);

        video.setUser(savedUser);

        Video savedVideo = videoRepository.save(video);

        assertEquals(video, savedVideo);
        assertEquals(savedUser, savedVideo.getUser());
    }


    @Test
    public void video_category_mapping_test(){

        Category category = new Category();

        category.setName("action");

        video.getCategories().add(category);

        Video savedVideo = videoRepository.save(video);

        assertEquals(video, savedVideo);
        assertTrue(video.getCategories().contains(category));
    }


    @Test
    public void find_video_by_category_test(){

        Category category = new Category();
        category.setName("action");

        Category savedCategory = categoryRepository.save(category);

        video.getCategories().add(savedCategory);

        Video video1 = videoRepository.save(video);

        List<Video> videoByCategoryIdList = videoRepository.findByCategoriesId(savedCategory.getId());
        assertTrue(videoByCategoryIdList.contains(video1));
    }


    @Test
    public void find_video_by_user_test(){

        User savedUser = userRepository.save(user);

        video.setUser(savedUser);

        Video savedVideo = videoRepository.save(video);

        List<Video> videoByUserIdList = videoRepository.findByUserId(user.getId());
        assertTrue(videoByUserIdList.contains(savedVideo));
    }


    @Test//this test should be in videoVote test instead
    public void videoVote_from_video_mapping_test(){

        User savedUser = userRepository.save(user);
        Video savedVideo = videoRepository.save(video);

        VideoVote videoVote = new VideoVote(savedUser, savedVideo);
        videoVote.setValue(true);

        VideoVote savedVideoVote = videoVoteRepository.save(videoVote);

        assertEquals(videoVote, savedVideoVote);
        assertNotNull(savedVideo);
        assertEquals(savedVideo, savedVideoVote.getVideo());
    }
}
