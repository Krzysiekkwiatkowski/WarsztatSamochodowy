<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 16.10.18
  Time: 14:02
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
Pracownicy:</br>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id </th>
        <th> Name </th>
        <th> Surname </th>
        <th> Address </th>
        <th> Phone </th>
        <th> Note </th>
        <th> Salary </th>
        <th> Orders </th>
        <th> Action </th>
    </tr>
    <c:forEach items="${employees}" var="employee">
        <tr>
            <td> ${employee.id} </td>
            <td> ${employee.name} </td>
            <td> ${employee.surname} </td>
            <td> ${employee.address} </td>
            <td> ${employee.phone} </td>
            <td> ${employee.note} </td>
            <td> ${employee.salary} </td>
            <td> <a href="http://localhost:8080/WarsztatSamochodowy/EmployeeOrders?id=${employee.id}"> Zlecenia </a> </td>
            <td> <a href="http://localhost:8080/WarsztatSamochodowy/Employees?action=edit&id=${employee.id}" > Edytuj </a> / <a href="http://localhost:8080/WarsztatSamochodowy/Employees?action=delete&id=${employee.id}" > Usuń </a> </td>
        </tr>
    </c:forEach>
</table>
<a href="http://localhost:8080/WarsztatSamochodowy/Employees?action=add" > Dodaj pracownika </a>
</br></br>
<%@include file="footer.jsp" %>
</body>
</html>