<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>User Edit</title>
    </head>

    <body>
        <h3>Edit User:</h3>
        <%
            @SuppressWarnings("unchecked")
            User user = (User) request.getAttribute("user");
        %>
        <form method="POST" action="user_save?user_id=<%=user.getId()%>">
            First Name: <input type="text" name="first_name" value="<%=user.getFirstName()%>"/>
            Last Name: <input type="text" name="last_name" value="<%=user.getLastName()%>"/>
            <input type="submit" value="Save" />
        </form>
        <br>
        <br>
    </body>
</html>