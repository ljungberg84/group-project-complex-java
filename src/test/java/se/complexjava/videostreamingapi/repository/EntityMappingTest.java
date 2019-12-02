package se.complexjava.videostreamingapi.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.complexjava.videostreamingapi.entity.Category;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.entity.Video;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class EntityMappingTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentVoteRepository commentVoteRepository;

    @Autowired
    VideoViewRepository videoVoteRepository;

    @Autowired
    VideoViewRepository videoViewRepository;

    @Autowired
    CategoryRepository categoryRepository;


    private static String firstName = "firstName";
    private static String lastName = "lastname";
    private static String email = "test@email.com";
    private static String personalId = "12345678";
    private static String password = "123";

    private static User user = new User();


    @BeforeAll
    public static void createUser(){

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPersonalId(personalId);
        user.setPassword(password);
    }


    @Test
    public void saveUserTest(){

        User savedUser = userRepository.save(user);

        assertEquals(user, savedUser);
    }


    @Test
    public void save_video_test(){


        Video video = new Video();

        video.setDescription("description");
        video.setTitle("title");

        Video savedVideo = videoRepository.save(video);

        assertEquals(video, savedVideo);

    }


    @Test
    public void video_user_mapping_test(){

        Video video = new Video();

        User savedUser = userRepository.save(user);

        video.setDescription("description");
        video.setTitle("title");
        video.setUser(savedUser);

        Video savedVideo = videoRepository.save(video);

        assertEquals(video, savedVideo);
        assertEquals(savedUser, savedVideo.getUser());
    }


    @Test
    public void video_category_mapping_test(){

        Video video = new Video();
        Category category = new Category();

        video.setDescription("description");
        video.setTitle("title");

        category.setName("action");

        video.getCategory().add(category);

        Video savedVideo = videoRepository.save(video);

        assertEquals(video, savedVideo);
        assertTrue(video.getCategory().contains(category));
    }


    @Test
    public void find_video_by_category_test(){

        Category category = new Category();
        category.setName("action");

        Category savedCategory = categoryRepository.save(category);

        Video video = new Video();
        video.getCategory().add(category);
        video.setDescription("description");
        video.setTitle("title");

        Video video1 = videoRepository.save(video);

        List<Video> videoByCategoryIdList = videoRepository.findByCategoryId(savedCategory.getId());
        assertTrue(videoByCategoryIdList.contains(video1));
    }
}
