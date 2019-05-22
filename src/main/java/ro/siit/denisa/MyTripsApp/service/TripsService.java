package ro.siit.denisa.MyTripsApp.service;

import ro.siit.denisa.MyTripsApp.model.Trips;

import java.util.List;

public interface TripsService {

    Trips saveTrips (Trips trips);

    Trips findByTripname(String tripname);

    List<Trips> findTripsByUserId(int userId);

    Trips findByTripId(int tripId);

   void deleteByTripId(Integer id);

   void deleteImg(String fileName);

   Trips findByTripIdAndUserId(int tripId, int userId);
   void delete(Trips trips);



}
