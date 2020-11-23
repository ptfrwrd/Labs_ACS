package controllers;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import services.UsersService;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.List;

@Controller
public class UsersController {
    private UsersService usersService;

    @Autowired
    @Qualifier(value = "usersService")
    public void setUsersService(UsersService service) {
        this.usersService = service;
    }

    @RequestMapping("/users")
    public String showUsers(Model model) throws ParserConfigurationException {
        List<User> users = usersService.getAllUsers();

        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElement("users");

        for (User user : users) {
            Element userNode = document.createElement("user");

            Element idNode = document.createElement("id");
            idNode.appendChild(document.createTextNode(user.getId().toString()));
            userNode.appendChild(idNode);

            Element firstNameNode = document.createElement("firstName");
            firstNameNode.appendChild(document.createTextNode(user.getFirstName()));
            userNode.appendChild(firstNameNode);

            Element lastNameNode = document.createElement("lastName");
            lastNameNode.appendChild(document.createTextNode(user.getLastName()));
            userNode.appendChild(lastNameNode);

            root.appendChild(userNode);
        }

        model.addAttribute("users", root);
        return "users";
    }
}
