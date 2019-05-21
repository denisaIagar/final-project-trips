package ro.siit.denisa.MyTripsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.siit.denisa.MyTripsApp.model.RoleRepository;
import ro.siit.denisa.MyTripsApp.model.User;
import ro.siit.denisa.MyTripsApp.model.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public interface UserService {

    void save(User user);

    User findByUsername(String username);



}


