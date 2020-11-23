<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Orders</title>
    </head>

    <body>
        <form method="POST" action="users">
            First Name: <input type="text" name="first_name" />
            Last Name: <input type="text" name="last_name" />
            <input type="submit" value="Add" />
        </form>

        <hr><ol> <%
            @SuppressWarnings("unchecked")
            List<User> users = (List<User>)request.getAttribute("users");
            if (users != null) {
                for (User user: users) { %>
                    <li> <%= user %> </li> <%
                }
            } %>
        </ol><hr>
     </body>
 </html>