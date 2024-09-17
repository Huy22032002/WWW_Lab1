<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <form method="post" action="control">
        <input type="hidden" name="action" value="login">
        Account ID: <input type="text" name="id"/> <br/>
        Password: <input type="password" name="password"> <br/>
        <input type="submit" value="login">

    </form>
</body>
</html>