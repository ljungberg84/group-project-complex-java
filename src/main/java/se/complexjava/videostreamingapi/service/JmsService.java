package se.complexjava.videostreamingapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import se.complexjava.videostreamingapi.entity.Video;
import se.complexjava.videostreamingapi.entity.VideoState;
import se.complexjava.videostreamingapi.repository.VideoRepository;

import java.util.Map;

@Service
public class JmsService {

    private static final String UPLOADER_TO_DATA_QUE = "uploader-to-data-que";
    private static final String ENCODER_TO_DATA_QUE = "encoder-to-data-que";
    private static final String DELETED_VIDEO_FILE_TOPIC = "delete-video-File-topic";

    private VideoRepository videoRepository;

    private Logger logger = LoggerFactory.getLogger(JmsService.class);

    @Autowired
    public JmsService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    @JmsListener(destination = UPLOADER_TO_DATA_QUE)
    public void setVideoUploaded(Map<String, String> message){
        long userId = Long.parseLong(message.get("userId"));
        long videoId = Long.parseLong(message.get("videoId"));
        try{
            if(message.get("status").equals("1")){
                Video video = videoRepository.findById(videoId).get();
                video.setState(VideoState.UPLOADED);
                videoRepository.save(video);
                logger.info("videostate for: {}&{} set to UPLOADED", userId, videoId);

            }else{
                videoRepository.deleteById(videoId);
                logger.info(String.format("setVideoUploaded(), removing video: {}&{} due to unsuccessful upload", userId, videoId));

            }
        }catch (NullPointerException e){
            logger.info(String.format("setVideoUploaded(), video state could not be updated because not found: userId: %s, videoId: %s", userId, videoId));
        }
    }


    @JmsListener(destination = ENCODER_TO_DATA_QUE)
    public void setVideoEncoded(Map<String, String>message){
        long userId = Long.parseLong(message.get("userId"));
        long videoId = Long.parseLong(message.get("videoId"));
        try{
            Video video = videoRepository.findById(videoId).get();
            video.setState(VideoState.ENCODED);
            videoRepository.save(video);
            logger.info("videostate for: {}&{} set to ENCODED", userId, videoId);
        }catch(NullPointerException e){
            logger.info(String.format("setVideoEncoded(), video state could not be updated because not found: userId: %s, videoId: %s", userId, videoId));
        }
    }


    @JmsListener(destination = DELETED_VIDEO_FILE_TOPIC)
    public void removeVideo(Map<String, String>message){
        long videoId = Long.parseLong(message.get("videoId"));
        videoRepository.deleteById(videoId);
    }
}
