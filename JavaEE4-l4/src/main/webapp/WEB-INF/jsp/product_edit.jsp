<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.Product"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Product Edit</title>
    </head>

    <body>
        <h3>Edit Product:</h3>
        <%
            @SuppressWarnings("unchecked")
            Product product = (Product) request.getAttribute("product");
        %>
        <form method="POST" action="product_save?product_id=<%=product.getId()%>">
            First Name: <input type="text" name="name" value="<%=product.getName()%>"/>
            <input type="submit" value="Save" />
        </form>
        <br>
        <br>
    </body>
</html>