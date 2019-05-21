package ro.siit.denisa.MyTripsApp.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import ro.siit.denisa.MyTripsApp.model.Trips;
import ro.siit.denisa.MyTripsApp.model.TripsRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
public class TripsServiceImpl implements TripsService {

    @Autowired
    TripsRepository tripsRepository;



    @Override
    public Trips saveTrips(Trips trips) {
        return tripsRepository.save(trips);
    }

    @Override
    public Trips findByTripname(String tripname) {
        return tripsRepository.findByTripname(tripname);
    }

    public static final String uploadingDir = System.getProperty("user.dir") + "/uploadingDir";

    public void addPhoto( MultipartFile files) throws Exception{
        StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(uploadingDir, files.getOriginalFilename());
            fileNames.append(files.getOriginalFilename());
                Files.write(fileNameAndPath, files.getBytes());
        }

     public void returnPhoto(HttpServletResponse response, @PathVariable String photoName) throws Exception{
         Path fileNameAndPath = Paths.get(TripsServiceImpl.uploadingDir, photoName);
         InputStream in = new FileInputStream(new File(fileNameAndPath.toUri()));
//                servletContext.getResourceAsStream("/WEB-INF/images/image-example.jpg");
         response.setContentType(MediaType.IMAGE_JPEG_VALUE);
         IOUtils.copy(in, response.getOutputStream());
     }

    @Override
    public List<Trips> findTripsByUserId(int tripId, int userId) {
        return tripsRepository.findTripsByUserId(userId);
    }

    @Override
    public Trips findByTripId(int tripId) {
        tripsRepository.findById(tripId);
        return null;
    }

    @Override
    public Trips deleteTrip(Trips trips) {
        tripsRepository.delete(trips);
        return trips;
    }

    @Override
    public Trips findTripByUserId(int tripId, int userId) {
        tripsRepository.findTripsByUserId(userId);
        return null;
    }


}
