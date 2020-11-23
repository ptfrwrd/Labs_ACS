package controllers;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import services.UsersService;

@Controller
public class UsersController {
    private UsersService usersService;

    @Autowired
    @Qualifier(value = "usersService")
    public void setUsersService(UsersService service) {
        this.usersService = service;
    }

    @RequestMapping("/users")
    public String showUsers(Model m) {
        m.addAttribute("users", usersService.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    protected String saveUser(@ModelAttribute("first_name") String firstName, @ModelAttribute("last_name") String lastName) {
        if (firstName != null && lastName != null) {
            usersService.saveUser(new User(firstName, lastName));
        }
        return "redirect:/users";
    }
}
