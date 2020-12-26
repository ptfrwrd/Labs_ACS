<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.Product"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Products</title>
    </head>

    <body>
        <h3>Add New Product:</h3>
        <form method="POST" action="product_add">
            Name: <input type="text" name="name" />
            <input type="submit" value="Add" />
        </form>
        <br>
        <br>

        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Actions</th>
            </tr>

            <%
                @SuppressWarnings("unchecked")
                List<Product> products = (List<Product>)request.getAttribute("products");
                if (products != null) {
                    for (Product product: products) {
                        %>
                            <tr>
                                <td> <%= product.getId() %> </td>
                                <td> <%= product.getName() %> </td>
                                <td>
                                    <a href="product_edit?product_id=<%=product.getId()%>">Edit</a>
                                    <a href="product_delete?product_id=<%=product.getId()%>">Delete</a>
                                </td>
                            </tr>
                        <%
                    }
                }
            %>
        </table>
     </body>
 </html>