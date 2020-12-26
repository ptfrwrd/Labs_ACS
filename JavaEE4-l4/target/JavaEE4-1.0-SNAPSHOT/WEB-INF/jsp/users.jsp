<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Users</title>
    </head>

    <body>
        <h3>Add New User:</h3>
        <form method="POST" action="user_add">
            First Name: <input type="text" name="first_name" />
            Last Name: <input type="text" name="last_name" />
            <input type="submit" value="Add" />
        </form>
        <br>
        <br>

        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Actions</th>
            </tr>

            <%
                @SuppressWarnings("unchecked")
                List<User> users = (List<User>)request.getAttribute("users");
                if (users != null) {
                    for (User user: users) {
                        %>
                            <tr>
                                <td> <%= user.getId() %> </td>
                                <td> <%= user.getFirstName() %> </td>
                                <td> <%= user.getLastName() %> </td>
                                <td>
                                    <a href="user_edit?user_id=<%=user.getId()%>">Edit</a>
                                    <a href="user_delete?user_id=<%=user.getId()%>">Delete</a>
                                </td>
                            </tr>
                        <%
                    }
                }
            %>
        </table>
     </body>
 </html>