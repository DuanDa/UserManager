<%--
  Created by IntelliJ IDEA.
  User: Devin
  Date: 10/2/15
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <h1>User Login</h1>
  <form action="/login.do" method="post">
    username: <input type="text" name="username"/>
    password: <input type="password" name="password"/>
    <input type="submit" value="Submit"/>
  </form>
</body>
</html>
