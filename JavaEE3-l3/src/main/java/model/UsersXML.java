package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
public class UsersXML {
    @XmlElement
    List<User> users = new ArrayList<>();

    public UsersXML() {
    }

    public UsersXML(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
