<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.TableUpdate"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Table Updates</title>
    </head>

    <body>
        <hr><ol> <%
            @SuppressWarnings("unchecked")
            List<TableUpdate> updates = (List<TableUpdate>)request.getAttribute("table_updates");
            if (updates != null) {
                for (TableUpdate update: updates) { %>
                    <li> <%= update %> </li> <%
                }
            } %>
        </ol><hr>
     </body>
 </html>