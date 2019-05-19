package ro.siit.denisa.MyTripsApp.service;

import org.springframework.web.multipart.MultipartFile;
import ro.siit.denisa.MyTripsApp.model.Trips;
import ro.siit.denisa.MyTripsApp.model.User;

import java.util.List;

public interface TripsService {

    Trips saveTrips (Trips trips);

    Trips findByTripname(String tripname);

    List<Trips> findTripsByUserId(int userId);

}
