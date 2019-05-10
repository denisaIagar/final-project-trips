package ro.siit.denisa.MyTripsApp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ro.siit.denisa.MyTripsApp.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
