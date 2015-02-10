<%--
  Created by IntelliJ IDEA.
  User: Devin
  Date: 10/2/15
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<html>
<head>
    <title></title>
</head>
<body>
  <h1>Manage Users</h1>
  <table>
    <tr>
      <tr><td>id</td><td>name</td></tr>
    </tr>
    <logic:iterate id="user" name="users">
      <tr><td>${user.id}</td><td>${user.name}</td></tr>
    </logic:iterate>
  </table>
</body>
</html>
