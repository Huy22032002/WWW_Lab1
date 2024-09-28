<%@ page import="fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities.Account" %><%--
  Created by IntelliJ IDEA.
  User: Riki
  Date: 16/09/2024
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h1>Dashboard</h1>
    <%
        HttpSession session1 = request.getSession();
        Account account = (Account) session1.getAttribute("account");
        if(account != null) {
            Object role = session1.getAttribute("role");
            String roleString = (String) role;
            switch (roleString){
                case "user":

    %>
    <h2>Welcome <%= account.getFullName() %></h2>
    <h3>Email: <%= account.getEmail()%></h3>
    <p>Pass: <%= account.getPassword()%></p>
    <p>Phone: <%= account.getPhone()%></p>
    <p>Status: <%= account.getStatus()%></p>

    <%
            break;
            case "admin":
    %>


<% break; }} %>

</body>
</html>
