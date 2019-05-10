package ro.siit.denisa.MyTripsApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class TripsController {

    public static final String uploadingDir =System.getProperty("user.dir") +"/uploadingDir/";

    @RequestMapping("trips")
    public String trips(Model model){
        File file = new File(uploadingDir);
        model.addAttribute("files", file.listFiles());
        return "trips";
    }

    @RequestMapping(value = "trips", method = RequestMethod.POST)
    public String tripsUploadingPost(@RequestParam("uploadingFiles")MultipartFile[]uploadingFiles) throws IOException {
        for(MultipartFile uploadedFile : uploadingFiles) {
            File file = new File(uploadingDir + uploadedFile.getOriginalFilename());
            uploadedFile.transferTo(file);
        }
        return "redirect:/";
    }

}
