<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.Order,model.User,model.Product"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Order Edit</title>
    </head>

    <body>
        <h3>Edit Order:</h3>
        <%
            @SuppressWarnings("unchecked")
            Order order = (Order) request.getAttribute("order");
        %>
        <form method="POST" action="order_save?order_id=<%=order.getId()%>">

            User:
            <select name="user_id">
                <%
                    List<User> users = (List<User>)request.getAttribute("users");
                    for (User user: users) {
                        %> <option value="<%=user.getId()%>" <%=user.getId() == order.getUser().getId() ? "selected" : ""%> ><%=user.getFirstName() + " " + user.getLastName()%></option> <%
                    }
                %>
            </select>

            Product:
            <select name="product_id">
                <%
                    List<Product> products = (List<Product>)request.getAttribute("products");
                    for (Product product: products) {
                        %> <option value="<%=product.getId()%>" <%=product.getId() == order.getProduct().getId() ? "selected" : ""%> ><%=product.getName()%></option> <%
                    }
                %>
            </select>

            Amount: <input name="amount" type="number" value="<%=order.getAmount()%>">

            <input type="submit" value="Save" />
        </form>
        <br>
        <br>
    </body>
</html>