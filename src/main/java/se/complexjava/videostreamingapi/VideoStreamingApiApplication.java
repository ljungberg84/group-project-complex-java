package se.complexjava.videostreamingapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class VideoStreamingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoStreamingApiApplication.class, args);
	}
}
