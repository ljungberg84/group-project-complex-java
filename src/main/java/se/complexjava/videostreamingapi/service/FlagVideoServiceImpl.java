package se.complexjava.videostreamingapi.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import se.complexjava.videostreamingapi.entity.FlagVideo;
import se.complexjava.videostreamingapi.model.FlagVideoModel;
import se.complexjava.videostreamingapi.repository.FlagVideoRepository;

public class FlagVideoServiceImpl implements FlagVideoService{

	
	private FlagVideoRepository repository;

	public FlagVideoServiceImpl(FlagVideoRepository repository) {
		this.repository = repository;
	}
	
	
	@Override
	public FlagVideoModel createFlag(FlagVideoModel flagModel) throws Exception {
		
		FlagVideo flag = FlagVideo.fromModel(flagModel);
		flag.setDateFlagged(Instant.now());
		
		return FlagVideoModel.fromEntity(repository.save(flag));
	}

	@Override
	public FlagVideoModel getFlag(long userId, long videoId) throws Exception {
		
		Optional<FlagVideo> flag = repository.findById(userId, videoId);
		
		return FlagVideoModel.fromEntity(flag.get());
	}

	@Override
	public List<FlagVideoModel> getFlaggedVideos() throws Exception {

		Iterable<FlagVideo> entities = repository.findAll();
		
		return FlagVideoModel.fromEntity(entities);
	}

	@Override
	public void deleteFlag(long userId, long videoId) throws Exception {
		repository.deleteById(userId, videoId);
	}

	@Override
	public FlagVideoModel updateDescription(FlagVideoModel flagModel, long userId, long videoId) throws Exception {
		
		Optional<FlagVideo> flag = repository.findById(userId, videoId);
		
		FlagVideo updatedFlag = flag.get();
		updatedFlag.setDescription(flagModel.getDescription());
		
		return FlagVideoModel.fromEntity(repository.save(updatedFlag));
	}
	
}
