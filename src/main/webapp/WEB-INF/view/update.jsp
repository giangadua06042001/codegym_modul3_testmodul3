<%--
  Created by IntelliJ IDEA.
  User: LAPTOP CU PHO YEN
  Date: 5/29/2023
  Time: 11:08 AM
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
    <h1>Employees Management</h1>
    <h2>
        <a href="/employee?choice=users">List All Users</a>
    </h2>
</center>
<div align="center">
    <form method="post" action="update">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New employees</h2>
            </caption>
            <tr>
                <th>Employees Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45"/>
                </td>
            </tr>

            <tr>
                <th>Employees Email:</th>
                <td>
                    <input type="text" name="email" id="email" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Employees address:</th>
                <td>
                    <input type="text" name="address" id="address" size="15"/>
                </td>
            </tr>
            <tr>
                <th>Employees phone:</th>
                <td>
                    <input type="text" name="phone" id="phone" size="15"/>
                </td>
            </tr>
            <tr>
                <th>Employees salary:</th>
                <td>
                    <input type="number" name="salary" id="salary" size="15"/>
                </td>
            </tr>
            <tr>
                <th>Employees department:</th>
                <td>
                    <input type="number" name="proname" id="proname" size="15"/>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
