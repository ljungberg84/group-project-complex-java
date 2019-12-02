package se.complexjava.videostreamingapi.entity.composite_key;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class VideoViewKey implements Serializable {


    @Column(name = "user_id")
    Long userId;

    @Column(name = "video_id")
    Long videoId;


}
