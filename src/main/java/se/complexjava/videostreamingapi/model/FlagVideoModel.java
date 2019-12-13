package se.complexjava.videostreamingapi.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.complexjava.videostreamingapi.entity.FlagVideo;
import se.complexjava.videostreamingapi.entity.User;
import se.complexjava.videostreamingapi.entity.Video;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlagVideoModel implements Serializable{

	
	private long id;
	User user;
	Video video;
	Instant dateFlagged;
	
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static FlagVideoModel fromEntity(FlagVideo entity) {
		return modelMapper.map(entity, FlagVideoModel.class);
	}
	
	public static List<FlagVideoModel> fromEntity(Iterable<FlagVideo> entities) {
		
		List<FlagVideoModel> models = new ArrayList<>();
		for(FlagVideo entity : entities) {
			models.add(fromEntity(entity));
		}
		
		return models;
	}
	
}
