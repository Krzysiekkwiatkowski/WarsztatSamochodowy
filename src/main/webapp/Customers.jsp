<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 16.10.18
  Time: 19:41
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
<form action="/WarsztatSamochodowy/Customers?search=" method="get">
    <input type="text" name="search">
    <input type="submit" value="Wyszukaj">
</form>
Klienci:</br>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id </th>
        <th> Name </th>
        <th> Surname </th>
        <th> Vehicles </th>
        <th> Orders </th>
        <th> Action </th>
    </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td> ${customer.id} </td>
            <td> ${customer.name} </td>
            <td> ${customer.surname} </td>
            <td> <a href="http://localhost:8080/WarsztatSamochodowy/CustomerVehicles?id=${customer.id}" > Pojazdy </a> </td>
            <td> <a href="http://localhost:8080/WarsztatSamochodowy/CustomerOrders?id=${customer.id}" > Zlecenia </a> </td>
            <td> <a href="http://localhost:8080/WarsztatSamochodowy/Customers?action=edit&id=${customer.id}" > Edytuj </a> / <a href="http://localhost:8080/WarsztatSamochodowy/Customers?action=delete&id=${customer.id}" > Usuń </a> </td>
        </tr>
    </c:forEach>
</table>
<a href="http://localhost:8080/WarsztatSamochodowy/Customers?action=add" > Dodaj klienta </a>
</br></br>
<%@include file="footer.jsp" %>
</body>
</html>
