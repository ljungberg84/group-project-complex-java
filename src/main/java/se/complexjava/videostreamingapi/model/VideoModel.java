package se.complexjava.videostreamingapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoModel extends Model implements Serializable {

    private String title;

    private String description;
}
