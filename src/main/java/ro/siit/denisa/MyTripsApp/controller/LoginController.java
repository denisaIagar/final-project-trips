package ro.siit.denisa.MyTripsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.siit.denisa.MyTripsApp.Validator.UserValidator;
import ro.siit.denisa.MyTripsApp.service.SecurityService;
import ro.siit.denisa.MyTripsApp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iagar Denisa
 * the Login controller class recive request and and prepare the model for the View to present
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    /**
     * the login method validates if the credentials for user name and password are correct
     * if password and user name don`t match an error message will be display
     * @param model
     * @param error
     * @param logout
     * @return
     */

    @GetMapping("/login")
    public String login( Model model,String error,String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }


    /**
     * this method use authentication and security for the logout request
     * @param request
     * @param response
     * @return redirect to login page
     */

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication !=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }return "redirect:/login?logout";



    }

}
