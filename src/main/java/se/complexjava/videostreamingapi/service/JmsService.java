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
        String title = message.get("title");
        try{
            if(message.get("status").equals("1")){
                Video video = videoRepository.findByUserIdAndTitle(userId, title);
                video.setState(VideoState.UPLOADED);
                videoRepository.save(video);
            }else{
                videoRepository.deleteByUserIdAndTitle(userId, title);
            }
        }catch (NullPointerException e){
            logger.info(String.format("setVideoUploaded(), video state could not be updated because not found: userId: %s, title: %s", userId, title));
        }
    }


    @JmsListener(destination = ENCODER_TO_DATA_QUE)
    public void setVideoEncoded(Map<String, String>message){
        long userId = Long.parseLong(message.get("userId"));
        String title = message.get("title");
        try{
            Video video = videoRepository.findByUserIdAndTitle(userId, title);
            video.setState(VideoState.ENCODED);
            videoRepository.save(video);
        }catch(NullPointerException e){
            logger.info(String.format("setVideoEncoded(), video state could not be updated because not found: userId: %s, title: %s", userId, title));
        }
    }


    @JmsListener(destination = DELETED_VIDEO_FILE_TOPIC)
    public void removeVideo(Map<String, String>message){
        long userId = Long.parseLong(message.get("userId"));
        String title = message.get("title");

        videoRepository.deleteByUserIdAndTitle(userId, title);
    }
}
