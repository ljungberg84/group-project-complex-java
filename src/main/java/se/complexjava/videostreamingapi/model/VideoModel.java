package se.complexjava.videostreamingapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import se.complexjava.videostreamingapi.entity.Video;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoModel implements Serializable {

    private long id;

    private String title;

    private String description;

    private long userId;


    private static ModelMapper modelMapper = new ModelMapper();


    public static VideoModel fromEntity(Video video){

        return modelMapper.map(video, VideoModel.class);
    }

    public static List<VideoModel> fromEntity(Iterable<Video> entities){

        List<VideoModel> models = new ArrayList<>();
        for (Video video : entities) {
            models.add(fromEntity(video));
        }

        return models;
    }
}
