<%--
  Created by IntelliJ IDEA.
  User: hdhamee
  Date: 12/13/16
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<html>
<head>
  <title></title>
</head>
<body>
<form action="/login" method="post">
  Name: <input type="text" name="uname"> <br/>
  Pass: <input type="text" name="pass"> <br/>
  <input type="checkbox" name="remember" value="true">
  <input type="submit">
</form>
</body>
</html>
