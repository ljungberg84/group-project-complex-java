package se.complexjava.videostreamingapi.entity.composite_key;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.IdClass;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VideoVoteKey implements Serializable {

    //@GeneratedValue
    @Column(name = "user_id")
    long userId;

    //@GeneratedValue
    @Column(name = "video_id")
    long videoId;
}
