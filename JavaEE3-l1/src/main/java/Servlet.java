import business.UsersDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UsersServlet", urlPatterns = {"/users"})
public class Servlet extends HttpServlet {
    UsersDao usersDao = new UsersDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", usersDao.getAllUsers());
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        if (firstName != null && lastName != null)
            usersDao.persist(new User(firstName, lastName));

        doGet(request, response);
    }

    public static void main(String[] args) {
        UsersDao usersDao = new UsersDao();
        for (User user : usersDao.getAllUsers()) {
            System.out.println(user);
        }
    }
}
