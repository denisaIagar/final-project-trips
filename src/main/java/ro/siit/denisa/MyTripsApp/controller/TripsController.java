package ro.siit.denisa.MyTripsApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ro.siit.denisa.MyTripsApp.model.Trips;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class TripsController {

    public static final String uploadingDir =System.getProperty("user.dir") +"/uploadingDir";

    @RequestMapping("trips")
    public String tripsUpload(Model model){
        model.addAttribute("trip","new trip");
        return "trips";
    }
    @RequestMapping("add-trips")
    public String tripsAdd(Model model){
        model.addAttribute("trip",new Trips());
        return "add-trips";
    }


    @RequestMapping(value = "/add-trip-photo",method = RequestMethod.POST)
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
        return "redirect:/trips";
    }
}
