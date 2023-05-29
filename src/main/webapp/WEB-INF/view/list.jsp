<%--
  Created by IntelliJ IDEA.
  User: LAPTOP CU PHO YEN
  Date: 5/29/2023
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/employee?choice=create">Add new employee</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>phone</th>
            <th>salary</th>
            <th>department</th>
        </tr>
        <c:forEach var="employees" items="${employees}">
            <tr>
                <td><c:out value="${employees.id}"/></td>
                <td><c:out value="${employees.name}"/></td>
                <td><c:out value="${employees.email}"/></td>
                <td><c:out value="${employees.address}"/></td>
                <td><c:out value="${employees.phone}"/></td>
                <td><c:out value="${employees.salary}"/></td>
                <td><c:out value="${employees.department}"/></td>
                <td>
                    <a href="/employee?choice=update&id=${employees.id}">Edit</a>
                    <a href="/employee?choice=delete&id=${employees.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div>
    <input type="text" name="name" size="45" id="name">
    <button type="submit" value="search">search</button>
</div>

</body>
</html>
