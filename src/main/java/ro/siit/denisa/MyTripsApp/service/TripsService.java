package ro.siit.denisa.MyTripsApp.service;

import ro.siit.denisa.MyTripsApp.model.Trips;

import java.util.List;

public interface TripsService {

    Trips saveTrips (Trips trips);

    Trips findByTripname(String tripname);

    List<Trips> findTripsByUserId(int tripId, int userId);

    Trips findByTripId(int tripId);

    Trips deleteTrip(Trips trips);

    Trips findTripByUserId(int tripId, int userId);

}
