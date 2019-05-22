package ro.siit.denisa.MyTripsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.siit.denisa.MyTripsApp.model.Trips;
import ro.siit.denisa.MyTripsApp.model.TripsRepository;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * provide service for register a trip
 *
 * @author Iagar Denisa
 */

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

    /**
     * addPhoto method is saves the uploaded photos from browser
     */

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

    @Override
    public Trips findByTripId(int tripId) {
        tripsRepository.findById(tripId);
        return null;
    }

    @Override
    public void deleteByTripId(Integer id) {
        tripsRepository.deleteById(id);
    }

    @Override
    public void deleteImg(String fileName) {
        try{
            Files.deleteIfExists(Paths.get(uploadingDir, fileName));
        }catch (NoSuchFileException e){
            System.out.println("No such file/directory");
        }catch (DirectoryNotEmptyException e){
            System.out.println("Empty directory");
        }catch (IOException e){
            System.out.println("Invalid permissions.");
        }
    }

  @Override
    public Trips findByTripIdAndUserId(int tripId, int userId) {
       return  tripsRepository.findByTripIdAndUserId(tripId, userId);

    }

    @Override
    public void delete(Trips trips) {
        tripsRepository.delete(trips);
    }

//    @Override
//    public void findByTripIdAndUserId(int tripId, int userId) {
//        tripsRepository.
//    }


}
