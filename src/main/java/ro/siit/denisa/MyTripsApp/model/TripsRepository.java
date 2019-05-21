package ro.siit.denisa.MyTripsApp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripsRepository extends JpaRepository <Trips, Integer>{
    Trips findByTripname(String tripname);


    @Query(value = "select * from trip where user_id=:value and deleteT=false", nativeQuery = true)
    List<Trips> findTripsByUserId(@Param("value") int userId);

    @Query(value = "select * from trip where trip_id=:value and deletet=false", nativeQuery = true)
    Trips findByTripId(@Param("value") int tripId);

    @Query(value = "select * from trip where name=:value1 and user_id=:value2 and deletet=false", nativeQuery = true)
    Trips findByTripByUserId(@Param("value1") String name, @Param("value2") int userId);
}