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
        m.addAttribute("users", usersService.getAll());
        return "users";
    }

    @RequestMapping(value = "/user_add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("first_name") String firstName, @ModelAttribute("last_name") String lastName) {
        if (firstName != null && lastName != null) {
            User user = new User(firstName, lastName);
            usersService.create(user);
        }
        return "redirect:/users";
    }

    @RequestMapping("/user_edit")
    public String editUser(@ModelAttribute("user_id") Long userId, Model m) {
        User user = usersService.getById(userId);
        m.addAttribute("user", user);
        m.addAttribute("user_id", userId);
        return "user_edit";
    }

    @RequestMapping("/user_save")
    public String saveUser(
            @ModelAttribute("user_id") Long userId,
            @ModelAttribute("first_name") String firstName,
            @ModelAttribute("last_name") String lastName
    ) {
        usersService.update(new User(userId, firstName, lastName));
        return "redirect:/users";
    }

    @RequestMapping("/user_delete")
    public String deleteUser(@ModelAttribute("user_id") Long userId) {
        usersService.delete(userId);
        return "redirect:/users";
    }
}
