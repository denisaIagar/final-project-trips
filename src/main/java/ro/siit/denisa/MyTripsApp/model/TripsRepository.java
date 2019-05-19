package ro.siit.denisa.MyTripsApp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripsRepository extends JpaRepository <Trips, Integer>{
    Trips findByTripname(String tripname);


    @Query(value = "select * from trip where user_id=:value",
    nativeQuery = true)
    List<Trips> findTripsByUserId(@Param("value") int userId);
}
