<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 16.10.18
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
</br></br>
Zlecenia pracownika:</br>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id </th>
        <th> Received </th>
        <th> Planned </th>
        <th> Started </th>
        <th> Problem </th>
        <th> Repair </th>
        <th> Status </th>
        <th> Brand </th>
        <th> Model </th>
    </tr>
    <c:forEach items="${employeeOrders}" var="employeeOrder">
        <tr>
            <td> ${employeeOrder.id} </td>
            <td> ${employeeOrder.received} </td>
            <td> ${employeeOrder.planned} </td>
            <td> ${employeeOrder.started} </td>
            <td> ${employeeOrder.problem} </td>
            <td> ${employeeOrder.repair} </td>
            <td> ${employeeOrder.status} </td>
            <td> ${employeeOrder.brand} </td>
            <td> ${employeeOrder.model} </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@include file="footer.jsp" %>
</body>
</html>
