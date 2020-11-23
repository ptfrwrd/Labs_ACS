package controllers;

import model.User;
import model.UsersXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import services.UsersService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private UsersService usersService;

    @Autowired
    @Qualifier(value = "usersService")
    public void setUsersService(UsersService service) {
        this.usersService = service;
    }

    @GetMapping("/rest_users_json")
    public List<User> allUsersJson() {
        return usersService.getAllUsers();
    }

    @GetMapping(value = "/rest_users_xml", produces = {MediaType.APPLICATION_XML_VALUE})
    public UsersXML allUsersXml() {
        return new UsersXML(usersService.getAllUsers());
    }

    @PostMapping("/rest_add_user")
    @ResponseBody
    protected User addUser(@RequestParam(name = "first_name") String firstName, @RequestParam(name = "last_name") String lastName) {
        if (firstName != null && lastName != null) {
            User user = new User(firstName, lastName);
            usersService.saveUser(user);
            return user;
        }
        throw new IllegalArgumentException("First name or last name were not provided!");
    }
}
