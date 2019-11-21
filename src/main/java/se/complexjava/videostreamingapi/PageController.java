package se.complexjava.videostreamingapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Controller()
public class PageController {

    //@Value("$video.location")
    //private String videoLocation;
    private String videoLocation = "./videos";

    @GetMapping("watch/")
    public String index(Model model) throws Exception{
        // getting all of the files in video folder

        Stream videos = Files.list(Paths.get(videoLocation)).map(s-> s.toString());
        model.addAttribute("videos", videos);

        return "index";
    }

//    @GetMapping("/")
//    fun index(model: Model): String {
//        // getting all of the files in video folder
//        val videos = Files.list(Paths.get(videoLocation)).map { it.fileName.toString() }.toList()
//        model.addAttribute("videos", videos)
//        return "index"
//    }



    @GetMapping("watch/{name}")
    public String video(@PathVariable(name = "name") String videoName, Model model){

        model.addAttribute("videoName", videoName);

        return "videoView";
    }
}
