package ro.siit.denisa.MyTripsApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class TripsController {

    public static final String uploadingDir =System.getProperty("user.dir") +"/uploadingDir";

//    @RequestMapping("/trips")
//    public String trips(Model model){
//        File file = new File(uploadingDir);
//        model.addAttribute("files", file.listFiles());
//        return "trips";
//    }
//
//    @RequestMapping(value = "/add-trips", method = RequestMethod.POST)
//    public String tripsUploadingPost(@RequestParam("uploadingFiles")MultipartFile[]uploadingFiles) throws IOException {
//        for(MultipartFile uploadedFile : uploadingFiles) {
//            File file = new File(uploadingDir + uploadedFile.getOriginalFilename());
//            uploadedFile.transferTo(file);
//        }
//        return "redirect:/add-trips";
//    }


    @RequestMapping("trips")
    public String tripsUpload(Model model){
        return "trips";
    }
    @RequestMapping("/add-trips")
    public String addTrips(Model model, @RequestParam("files")MultipartFile[]files) {
        StringBuilder fileNames = new StringBuilder();
        for (MultipartFile file : files) {
            Path fileNameAndPath = Paths.get(uploadingDir, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            try {
                Files.write(fileNameAndPath, file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("msg", "Photo uploaded");
        return "redirect:/add-trips";
    }
}
