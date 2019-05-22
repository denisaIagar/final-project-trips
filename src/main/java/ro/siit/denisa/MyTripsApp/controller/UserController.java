package ro.siit.denisa.MyTripsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.denisa.MyTripsApp.Validator.UserValidator;
import ro.siit.denisa.MyTripsApp.model.User;
import ro.siit.denisa.MyTripsApp.service.SecurityService;
import ro.siit.denisa.MyTripsApp.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping( path = "/registration")
    public String registration(Model model){
    model.addAttribute("userForm", new User());
    return "registration";
    }

    /**
     * this method is saving all the user who want to create an account
     *
     * @param userForm
     * @param bindingResult
     * @return
     */

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult) {
      userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/trips";
    }



}
