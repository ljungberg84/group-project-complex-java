package se.complexjava.videostreamingapi.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.complexjava.videostreamingapi.entity.composite_key.FlagVideoKey;
import se.complexjava.videostreamingapi.model.FlagVideoModel;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FlagVideo {

	@EmbeddedId
	private FlagVideoKey id = new FlagVideoKey();
	
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
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static FlagVideo fromModel(FlagVideoModel model) {
		return modelMapper.map(model, FlagVideo.class);
	}
	
	public static List<FlagVideo> fromModel(Iterable<FlagVideoModel> models){
		
		List<FlagVideo> entities = new ArrayList<>();
		for(FlagVideoModel model : models) {
			entities.add(fromModel(model));
		}
		
		return entities;
	}
}
