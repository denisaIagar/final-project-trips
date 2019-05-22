package ro.siit.denisa.MyTripsApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.siit.denisa.MyTripsApp.model.RoleRepository;
import ro.siit.denisa.MyTripsApp.model.User;
import ro.siit.denisa.MyTripsApp.model.UserRepository;

import javax.management.relation.Role;
import java.util.HashSet;

/**
 * provide service for register a user
 *
 * @author Iagar Denisa
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

