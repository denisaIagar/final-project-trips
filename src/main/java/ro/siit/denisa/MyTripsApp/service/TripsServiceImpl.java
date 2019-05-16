package ro.siit.denisa.MyTripsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.siit.denisa.MyTripsApp.model.Trips;
import ro.siit.denisa.MyTripsApp.model.TripsRepository;

@Service
public class TripsServiceImpl {

    @Autowired
    TripsRepository tripsRepository;

    public Trips findByTripName(String tripname){
       return tripsRepository.findByTripName(tripname);
    }

    public Trips saveTrips(Trips trips){
        return tripsRepository.save(trips);
    }


}
