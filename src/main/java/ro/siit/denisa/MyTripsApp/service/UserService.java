package ro.siit.denisa.MyTripsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.siit.denisa.MyTripsApp.model.User;
import ro.siit.denisa.MyTripsApp.model.UserRepository;

@Service
public class UserService  {

    @Autowired
    static

    UserRepository userRepository;

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public static User saveUser(User a){
       return userRepository.save(a);
    }
}
