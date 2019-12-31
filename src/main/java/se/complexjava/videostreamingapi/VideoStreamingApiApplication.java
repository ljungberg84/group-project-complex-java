package se.complexjava.videostreamingapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;


@EnableJms
@SpringBootApplication
public class VideoStreamingApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(VideoStreamingApiApplication.class, args);

	}
}
