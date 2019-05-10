package ro.siit.denisa.MyTripsApp.service;



public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
