package se.complexjava.videostreamingapi.entity.composite_key;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VideoViewKey implements Serializable {

    Long userId;

    Long videoId;
}
