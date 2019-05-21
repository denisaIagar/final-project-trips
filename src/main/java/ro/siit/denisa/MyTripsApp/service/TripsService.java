package ro.siit.denisa.MyTripsApp.service;

import ro.siit.denisa.MyTripsApp.model.Trips;

import java.util.List;

public interface TripsService {

    Trips saveTrips (Trips trips);

    Trips findByTripname(String tripname);

    List<Trips> findTripsByUserId(int userId);

    Trips findByTripId(int tripId);
}
