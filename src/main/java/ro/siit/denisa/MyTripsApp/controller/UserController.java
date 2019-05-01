package ro.siit.denisa.MyTripsApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.denisa.MyTripsApp.model.User;
import ro.siit.denisa.MyTripsApp.service.UserService;

import javax.validation.Valid;

import static ro.siit.denisa.MyTripsApp.service.UserService.*;

@Controller
@RequestMapping(path = "/profile")
public class UserController {

    @Autowired
    UserService userService;

@GetMapping( path = "")
    public ModelAndView getAllUsers(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("users");
        mv.addObject("users", userService.getAllUsers());
        return mv;
    }


    @GetMapping(path = "/add")
    public String showUser(Model model){
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping(path = "/add")
    public String saveNewAuthor(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "add-user";
        }
        UserService.saveUser(user);
        return "redirect:/authors";
    }
}
