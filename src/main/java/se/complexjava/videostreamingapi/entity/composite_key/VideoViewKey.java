package se.complexjava.videostreamingapi.entity.composite_key;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VideoViewKey implements Serializable {

    //@Column(name = "userId")
    Long userId;

    //@Column(name = "videoId")
    Long videoId;


}
