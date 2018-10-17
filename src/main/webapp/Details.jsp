<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 17.10.18
  Time: 18:25
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
Szczegóły:</br>
<table border="1px solid black" style="text-align: center">
    <tr>
        <th> Id </th>
        <th> Received </th>
        <th> Planned </th>
        <th> Started </th>
        <th> Employee's id </th>
        <th> Problem </th>
        <th> Repair </th>
        <th> Status id </th>
        <th> Vehicle's id </th>
        <th> Cost </th>
        <th> Parts </th>
        <th> Employee's salary </th>
        <th> Time </th>
    </tr>
        <tr>
            <td> <c:out value="${order.id}" /> </td>
            <td> <c:out value="${order.received}" /> </td>
            <td> <c:out value="${order.planned}" /> </td>
            <td> <c:out value="${order.started}" /> </td>
            <td> <c:out value="${order.employee_id}" /> </td>
            <td> <c:out value="${order.problem}" /> </td>
            <td> <c:out value="${order.repair}" /> </td>
            <td> <c:out value="${order.status_id}" /> </td>
            <td> <c:out value="${order.vehicle_id}" /> </td>
            <td> <c:out value="${order.cost}" /> </td>
            <td> <c:out value="${order.parts}" /> </td>
            <td> <c:out value="${order.employeeSalary}" /> </td>
            <td> <c:out value="${order.time}" /> </td>
        </tr>
</table>
<a href="http://localhost:8080/WarsztatSamochodowy/Orders"> Cofnij </a>
</br></br>
<%@include file="footer.jsp" %>
</body>
</html>
