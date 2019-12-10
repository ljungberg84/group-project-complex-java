package se.complexjava.videostreamingapi.entity;

import java.time.Instant;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.complexjava.videostreamingapi.entity.composite_key.FlagKey;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Flag {

	@EmbeddedId
	private FlagKey id = new FlagKey();
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@MapsId("videoId")
	@JoinColumn(name = "video_id")	
	private Video video;
	
	private String description;
	
	private Instant dateFlagged;
}
