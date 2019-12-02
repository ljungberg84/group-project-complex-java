package se.complexjava.videostreamingapi.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.model.VideoModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_videos",
            joinColumns = {@JoinColumn(name = "video_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private User uploadedByUser;

    @OneToMany(mappedBy = "video")
    private Set<VideoVote> videoVotes;

    private static ModelMapper modelMapper = new ModelMapper();


    public static Video fromModel(VideoModel video){

        return modelMapper.map(video, Video.class);
    }

    public static List<Video> fromModel(Iterable<VideoModel> models){

        List<Video> entities = new ArrayList<>();
        for (VideoModel video : models) {
            entities.add(fromModel(video));
        }

        return entities;
    }
}
