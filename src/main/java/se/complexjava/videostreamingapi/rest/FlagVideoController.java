package se.complexjava.videostreamingapi.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.complexjava.videostreamingapi.entity.FlagVideo;
import se.complexjava.videostreamingapi.model.FlagVideoModel;
import se.complexjava.videostreamingapi.service.FlagVideoService;

@RestController
@RequestMapping("videos/flags")
public class FlagVideoController {

	private FlagVideoService flagVideoService;
	
	public FlagVideoController(FlagVideoService flagVideoService) {
		this.flagVideoService = flagVideoService;
	}
	
	@PostMapping
	public ResponseEntity<FlagVideoModel> createFlag(@Valid @RequestBody FlagVideoModel flag) throws Exception{
		 
		return ResponseEntity.status(HttpStatus.CREATED).body(flagVideoService.createFlag(flag));
	}
	
	@GetMapping
	public ResponseEntity<Iterable<FlagVideoModel>> getAllFlags() throws Exception{
		
		return ResponseEntity.status(HttpStatus.OK).body(flagVideoService.getFlaggedVideos());
	}
	
	@GetMapping("/{userId}/{videoId}")
	ResponseEntity<FlagVideoModel> getFlag(@PathVariable long userId, @PathVariable long videoId)throws Exception{

		return ResponseEntity.status(HttpStatus.OK).body(flagVideoService.getFlag(userId, videoId));
	}
	
	@DeleteMapping("/{userId}/{videoId}")
	ResponseEntity<FlagVideoModel> deleteFlag(@PathVariable long userId, @PathVariable long videoId)throws Exception{

		flagVideoService.deleteFlag(userId, videoId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}	
	@PutMapping("/{userId}/{videoId}")
	ResponseEntity<FlagVideoModel> updateFlag(@Valid @RequestBody FlagVideoModel flag, @PathVariable long userId, @PathVariable long videoId)throws Exception{

		return ResponseEntity.status(HttpStatus.OK).body(flagVideoService.updateDescription(flag, userId, videoId));
	}	
	
}
