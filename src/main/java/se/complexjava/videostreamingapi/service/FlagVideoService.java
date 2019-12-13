package se.complexjava.videostreamingapi.service;

import java.util.List;

import se.complexjava.videostreamingapi.model.FlagVideoModel;

public interface FlagVideoService {

	FlagVideoModel createFlag(FlagVideoModel flag) throws Exception;
	
	FlagVideoModel getFlag(long userId, long videoId) throws Exception;
	List<FlagVideoModel> getFlaggedVideos() throws Exception;
	
	FlagVideoModel updateDescription(FlagVideoModel flag, long userId, long videoId) throws Exception;
	
	void deleteFlag(long userId, long videoId) throws Exception;
}
