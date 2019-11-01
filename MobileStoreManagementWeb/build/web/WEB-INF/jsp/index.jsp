<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | TDT Mobile Store System Management</title>
    </head>

    <body>
        <h1>TDT Mobile Store System Management</h1>
        <form action="${pageContext.request.contextPath}/LoginController" method = "POST">
            Username: <input type = "text" name = "tbUsername" value = "" /><br/>
            Password: <input type = "password" name = "tbPassword" value = "" /><br/>
            <input type = "submit" value = "LogIn" name = "action" />
        </form>
    </body>
</html>
