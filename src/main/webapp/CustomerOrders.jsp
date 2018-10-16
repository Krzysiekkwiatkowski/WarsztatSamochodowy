<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 16.10.18
  Time: 22:26
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
Zamówienia klienta:</br>
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
    <c:forEach items="${orders}" var="order">
        <tr>
            <td> ${order.id} </td>
            <td> ${order.received} </td>
            <td> ${order.planned} </td>
            <td> ${order.started} </td>
            <td> ${order.problem} </td>
            <td> ${order.repair} </td>
            <td> ${order.status} </td>
            <td> ${order.brand} </td>
            <td> ${order.model} </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@include file="footer.jsp" %>
</body>
</html>
