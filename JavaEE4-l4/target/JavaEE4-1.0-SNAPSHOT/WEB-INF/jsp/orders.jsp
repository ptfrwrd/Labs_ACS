<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.Order,model.User,model.Product"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Orders</title>
    </head>

    <body>
        <h3>Add New Order:</h3>
        <form method="POST" action="order_add">

            User:
            <select name="user_id">
                <option disabled selected value> -- Select User -- </option>
                <%
                    List<User> users = (List<User>)request.getAttribute("users");
                    for (User user: users) {
                        %> <option value="<%=user.getId()%>"><%=user.getFirstName() + " " + user.getLastName()%></option> <%
                    }
                %>
            </select>

            Product:
            <select name="product_id">
                <option disabled selected value> -- Select Product -- </option>
                <%
                    List<Product> products = (List<Product>)request.getAttribute("products");
                    for (Product product: products) {
                        %> <option value="<%=product.getId()%>"><%=product.getName()%></option> <%
                    }
                %>
            </select>

            Amount: <input name="amount" type="number" value="">

            <input type="submit" value="Add" />
        </form>
        <br>
        <br>

        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>User Name</th>
                <th>Product</th>
                <th>Amount</th>
                <th>Actions</th>
            </tr>

            <%
                @SuppressWarnings("unchecked")
                List<Order> orders = (List<Order>)request.getAttribute("orders");
                if (orders != null) {
                    for (Order order: orders) {
                        %>
                            <tr>
                                <td> <%= order.getId() %> </td>
                                <td> <%= order.getUser().getFirstName() + " " +  order.getUser().getLastName()%> </td>
                                <td> <%= order.getProduct().getName() %> </td>
                                <td> <%= order.getAmount() %> </td>
                                <td>
                                    <a href="order_edit?order_id=<%=order.getId()%>">Edit</a>
                                    <a href="order_delete?order_id=<%=order.getId()%>">Delete</a>
                                </td>
                            </tr>
                        <%
                    }
                }
            %>
        </table>
     </body>
 </html>