<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,model.EmailNotificationsRule"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Email Notifications Rules</title>
    </head>

    <body>
        <h3>Add New Rule:</h3>
        <form method="POST" action="email_notifications_rule_add">
            Email: <input type="text" name="email" />
            Table Name:
            <select name="table_name">
                <option value="All">All</option>
                <option value="Users">Users</option>
                <option value="Products">Products</option>
                <option value="Orders">Orders</option>
            </select>
            Update Type:
            <select name="update_type">
                <option value="All">All</option>
                <option value="Insert">Insert</option>
                <option value="Update">Update</option>
                <option value="Delete">Delete</option>
            </select>
            <input type="submit" value="Add" />
        </form>
        <br>
        <br>

        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Table Name</th>
                <th>Update Type</th>
                <th>Actions</th>
            </tr>

            <%
                @SuppressWarnings("unchecked")
                List<EmailNotificationsRule> email_notifications_rules = (List<EmailNotificationsRule>)request.getAttribute("email_notifications_rules");
                if (email_notifications_rules != null) {
                    for (EmailNotificationsRule email_notifications_rule: email_notifications_rules) {
                        %>
                            <tr>
                                <td> <%= email_notifications_rule.getId() %> </td>
                                <td> <%= email_notifications_rule.getEmail() %> </td>
                                <td> <%= email_notifications_rule.getTableName() %> </td>
                                <td> <%= email_notifications_rule.getUpdateType() %> </td>
                                <td>
                                    <a href="email_notifications_rule_delete?email_notifications_rule_id=<%=email_notifications_rule.getId()%>">Delete</a>
                                </td>
                            </tr>
                        <%
                    }
                }
            %>
        </table>
     </body>
 </html>