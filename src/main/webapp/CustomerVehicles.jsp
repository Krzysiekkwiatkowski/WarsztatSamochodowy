<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 16.10.18
  Time: 21:53
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
Pojazdy:</br>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id </th>
        <th> Brand </th>
        <th> Model </th>
        <th> Year </th>
        <th> Registration </th>
        <th> Inspection </th>
    </tr>
    <c:forEach items="${vehicles}" var="vehicle">
        <tr>
            <td> ${vehicle.id} </td>
            <td> ${vehicle.brand} </td>
            <td> ${vehicle.model} </td>
            <td> ${vehicle.year} </td>
            <td> ${vehicle.registration} </td>
            <td> ${vehicle.inspection} </td>
        </tr>
    </c:forEach>
</table>
</br></br>
<%@include file="footer.jsp" %>
</body>
</html>
