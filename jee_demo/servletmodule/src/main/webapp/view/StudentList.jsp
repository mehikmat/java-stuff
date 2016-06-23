<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%--Here, we use the JSTL’s SQL tag query to make a SELECT query to the database.
 Note that the dataSource attribute refers to the JNDI resource name declared in the web.xml file
 But this is not recommended way--%>
<sql:query var="listStudent" dataSource="jdbc/UsersDB">
    select studentName, emailAddress from student;
</sql:query>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Student List</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Student</h2></caption>
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <c:forEach var="student" items="${listStudent.rows}">
            <tr>
                <td><c:out value="${student.studentName}" /></td>
                <td><c:out value="${student.emailAddress}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>