<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 17.10.18
  Time: 11:07
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
Pojazd:</br>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id </th>
        <th> Brand </th>
        <th> Model </th>
        <th> Year </th>
        <th> Registration </th>
        <th> Inspection </th>
    </tr>
    <tr>
        <td> <c:out value="${vehicle.id}"/> </td>
        <td> <c:out value="${vehicle.brand}"/> </td>
        <td> <c:out value="${vehicle.model}"/> </td>
        <td> <c:out value="${vehicle.year}"/> </td>
        <td> <c:out value="${vehicle.registration}"/> </td>
        <td> <c:out value="${vehicle.inspection}"/> </td>
    </tr>
</table>
Naprawy:</br>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id </th>
        <th> Oddany </th>
        <th> Naprawiony </th>
        <th> Problem </th>
        <th> Sposób </th>
        <th> Koszt </th>
    </tr>
    <c:forEach items="${history}" var="historyData">
        <tr>
            <td> ${historyData.id} </td>
            <td> ${historyData.received} </td>
            <td> ${historyData.started} </td>
            <td> ${historyData.problem} </td>
            <td> ${historyData.repair} </td>
            <td> ${historyData.cost} zł</td>
        </tr>
    </c:forEach>
</table>
<a href="http://localhost:8080/WarsztatSamochodowy/CustomerVehicles?id=${param.id}"> Cofnij </a>
</br></br>
<%@include file="footer.jsp" %>
</body>
</html>
