<%@ page import="Management.Emp" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Information</title>
</head>
<body>
<%
    List<Emp> work = (List<Emp>) request.getAttribute("work");


    if (work != null) {
%>
    <table border="1" style="width: 50%" height="50%">
        <thead>
            <tr>
                <th>Id</th>
                <th>Firstname</th>
                <th>Lastname</th>
            </tr>
        </thead>
        <tbody>
            <% for (Emp emp : work) { %>
            <tr>
                <td><%= emp.getId() %></td>
                <td><%= emp.getFirstname() %></td>
                <td><%= emp.getLastname() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
<%
    } else {
        out.println("No employee data available.");
    }
%>
</body>
</html>
