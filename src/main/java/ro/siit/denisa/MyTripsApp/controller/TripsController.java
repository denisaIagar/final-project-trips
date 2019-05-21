package ro.siit.denisa.MyTripsApp.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.denisa.MyTripsApp.model.Trips;
import ro.siit.denisa.MyTripsApp.model.User;
import ro.siit.denisa.MyTripsApp.service.SecurityService;
import ro.siit.denisa.MyTripsApp.service.TripsService;
import ro.siit.denisa.MyTripsApp.service.TripsServiceImpl;
import ro.siit.denisa.MyTripsApp.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class TripsController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    TripsService tripsService;

    @Autowired
    TripsServiceImpl tripsServiceimpl;



//    @RequestMapping("trips")
//    public String tripsUpload(Model model) {
//        model.addAttribute("trip", new Trips());
//        return "trips";
//    }

    @GetMapping("add-trips")
    public String tripsAdd(Model model) {
        model.addAttribute("trip", new Trips());
        return "add-trips";
    }

    @PostMapping("/add-trips")
    public String addTrips(@RequestParam("photoFirst1") MultipartFile photoFirst,
                          @RequestParam("photoSecond2") MultipartFile photoSecond,
                          @ModelAttribute("trip") @Valid Trips trip, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-trips";
        }

        try {
            tripsServiceimpl.addPhoto(photoFirst);
            tripsServiceimpl.addPhoto(photoSecond);
        } catch (Exception e) {
            e.printStackTrace();
            return "add-trips";
        }

        trip.setUser(userService.findByUsername(securityService.findLoggedInUsername()));

        trip.setPhoto1(photoFirst.getOriginalFilename());
        trip.setPhoto2(photoSecond.getOriginalFilename());
        tripsService.saveTrips(trip);

        return "redirect:/trips";
    }

    @RequestMapping(value = "/photos/{photoName}", method = RequestMethod.GET)
    public void getImageAsByteArray(HttpServletResponse response, @PathVariable String photoName) throws IOException {
        Path fileNameAndPath = Paths.get(TripsServiceImpl.uploadingDir, photoName);
        InputStream in = new FileInputStream(new File(fileNameAndPath.toUri()));
//                servletContext.getResourceAsStream("/WEB-INF/images/image-example.jpg");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }




    @GetMapping("/trips")
    public ModelAndView showTrips(@RequestParam(value = "tripx",required = false) Integer tripId) {
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        List<Trips> userTrips = tripsService.findTripsByUserId(tripId, user.getId());

        if (userTrips.isEmpty())
            return new ModelAndView("redirect:/add-trips");

        ModelAndView mv = new ModelAndView("trips");
        mv.addObject("trips", userTrips);

        if(tripId == null){
            mv.addObject("tr",userTrips.get(0));
        }else {
            Trips selectedTrip = new Trips();
            for(Trips t: userTrips)
                if(t.getTripId()== tripId)
                    selectedTrip = t;
                mv.addObject("tr",selectedTrip);
        }
        return mv;
    }


//    @PostMapping("/delete")
//    public String deleteTrip(@RequestParam(name= "id", required = false) int tripId){
//        Trips deleteByIdTrip= tripsService.findByTripId(tripId);
//        deleteByIdTrip.setDeleteT();
//        tripsService.saveTrips(deleteByIdTrip);
//
//            return "redirect:/trips";
//    }


//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//   public String deleteTrip(@RequestParam(name= "id") int tripId) {
////        User user = userService.findByUsername(securityService.findLoggedInUsername());
////        List<Trips> userTrips = tripsService.findTripsByUserId(tripId, user.getId());
//        Trips trips = tripsService.findTripsByUserId(tripId,
//                userService.findByUsername(securityService.findLoggedInUsername()).getId());
//
//
//        tripsService.deleteTrip(trips);
//        return "redirect:/trips";
//   }
    }

