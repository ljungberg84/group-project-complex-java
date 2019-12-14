package se.complexjava.videostreamingapi.service;

import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceCreationException;
import se.complexjava.videostreamingapi.exceptionhandling.exception.ResourceNotFoundException;
import se.complexjava.videostreamingapi.model.VideoModel;

import java.util.List;


public interface VideoService {

    public VideoModel createVideo(VideoModel video, long userId) throws ResourceCreationException;

    public VideoModel getVideo(long videoId) throws Exception;

    public List<VideoModel> getVideos();

    public void deleteVideo(long videoId);

    public VideoModel updateVideo(VideoModel video, long userId) throws Exception;

    public List<VideoModel> getVideosByUserId(long userId);

    public List<VideoModel> getVideosByCategoryId(long categoryId);
}
