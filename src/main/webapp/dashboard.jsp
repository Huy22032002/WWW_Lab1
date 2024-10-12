<%@ page import="fit.iuh.edu.vn.week01_lab_nguyenduchuy_20105761.entities.Account" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Riki
  Date: 16/09/2024
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <title>Dashboard</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <%
        HttpSession session1 = request.getSession();
        //phan quyen
        Account account = (Account) session1.getAttribute("account");
        //laay thong tin danh sach account
        ArrayList<Account> lstAcc = (ArrayList<Account>) session1.getAttribute("lstAcc");
        if(account != null) {
            Object role = session1.getAttribute("role");
            String roleString = (String) role;
            switch (roleString){
                case "user":

    %>
    <h2>Hello User <%= account.getFullName() %></h2>
    <h3>Email: <%= account.getEmail()%></h3>
    <p>Pass: <%= account.getPassword()%></p>
    <p>Phone: <%= account.getPhone()%></p>
    <p>Status: <%= account.getStatus()%></p>

    <%
            break;
            case "admin":
    %>
    <h1>Hello Admin <%= account.getFullName() %> </h1>
    <h2>User Table</h2>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Full Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(Account a : lstAcc){
        %>
        <tr>
            <td><%= a.getId()%></td>
            <td><%= a.getFullName()%></td>
            <td><%= a.getEmail()%></td>
            <td><%= a.getPhone()%></td>
            <td><%= a.getStatus()%></td>
        </tr>
        </tbody>
        <%
            }
        %>

    </table>
    <button id="openDialog">Them</button>
    <div id="dialog" title="Thêm thông tin">
        <form>
            <label for="accountId">Account ID:</label>
            <input type="text" id="accountId" name = "accountId"><br><br>
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" name ="fullName"><br><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name = "password"><br><br>
            <label for="email">Email:</label>
            <input type="email" id="email" name = "email"><br><br>
            <label for="phone">Phone:</label>
            <input type="tel" id="phone" name = "phone"><br><br>
        </form>
    </div>
    <button value="deleteUser">Xoa</button>
    <button value="updateUser">Sua</button>
    <button value="setRole">Cap quyen</button>

    <% break; }} %>
    <script>
        $(document).ready(function() {
            $("#dialog").dialog({
                autoOpen: false,
                modal: true,
                buttons: {
                    "Thêm": function() {
                        const id = $("#accountId").val();
                        const fullName = $("#fullName").val();
                        const password = $("#password").val();
                        const email = $("#email").val();
                        const phone = $("#phone").val();
                        // Gửi yêu cầu AJAX tới Servlet
                        $.ajax({
                            type: "POST",
                            url: "control", // URL của Servlet
                            data: {
                                action: "addUser",
                                accountID: id,
                                fullName: fullName,
                                password: password,
                                email: email,
                                phone: phone
                            },
                            success: function(response) {
                                alert("Thêm tài khoản thành công!");
                                location.reload(); // Reload lại trang sau khi thêm thành công
                            },
                            error: function() {
                                alert("Thêm tài khoản thất bại!");
                            }
                        });
                        $(this).dialog("close");
                    },
                    "Hủy": function() {
                        $(this).dialog("close");
                    }
                }
            });

            $("#openDialog").on("click", function() {
                $("#dialog").dialog("open");
            });
        });
    </script>
</body>
</html>
