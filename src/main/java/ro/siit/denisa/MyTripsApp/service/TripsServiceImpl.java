package ro.siit.denisa.MyTripsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ro.siit.denisa.MyTripsApp.model.Trips;
import ro.siit.denisa.MyTripsApp.model.TripsRepository;

import java.io.IOException;
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



    @Override
    public List<Trips> findTripsByUserId(int userId) {
        return tripsRepository.findTripsByUserId(userId);
    }

    public void removeTripById(Integer id){
       tripsRepository.deleteById(id);
    }
}
