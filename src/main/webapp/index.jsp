<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 14.10.18
  Time: 17:44
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
Aktualnie prowadzone naprawy:</br>
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
    <c:forEach items="${activeOrders}" var="activeOrder">
        <tr>
            <td> ${activeOrder.id} </td>
            <td> ${activeOrder.received} </td>
            <td> ${activeOrder.planned} </td>
            <td> ${activeOrder.started} </td>
            <td> ${activeOrder.problem} </td>
            <td> ${activeOrder.repair} </td>
            <td> ${activeOrder.status} </td>
            <td> ${activeOrder.brand} </td>
            <td> ${activeOrder.model} </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@include file="footer.jsp" %>
</body>
</html>
