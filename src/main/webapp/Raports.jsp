<%--
  Created by IntelliJ IDEA.
  User: oem
  Date: 18.10.18
  Time: 11:16
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
<c:if test="${empty raport}">
    <p>Wybierz rodzaj raportu:</p>
    <form action="/WarsztatSamochodowy/Raports" method="get">
        <c:set var="date" value="${date}"></c:set>
        Data początkowa: <input type="date" name="from"></br>
        Data końcowa: <input type="date" name="to"></br>
        <select name="type">
            <option value="working"> Roboczogodziny</option>
            <option value="benefits"> Zyski</option>
        </select></br></br>
        <input type="submit" value="Wybierz">
    </form>
</c:if>
<c:if test="${not empty workRaport}">
    <p>Przepracowane godziny:</p>
    <table border="1px solid black" style="text-align: center">
        <tr>
            <th> Imię i nazwisko pracownika </th>
            <th> Ilość roboczogodzin </th>
        </tr>
        <c:forEach items="${workRaport}" var="data">
            <tr>
                <td> ${data.employee} </td>
                <td> ${data.workedTime} </td>
            </tr>
        </c:forEach>
    </table>
    <a href="http://localhost:8080/WarsztatSamochodowy/Raports"> Cofnij </a>
</c:if>
<c:if test="${not empty benefitRaport}">
    <p>Zyski:</p>
    <table border="1px solid black" style="text-align: center">
        <tr>
            <th> Łączne wpływy </th>
            <th> Łączne koszty części </th>
            <th> Łączny koszt pracy </th>
            <th> Łączne czas pracy </th>
            <th> Zysk </th>
        </tr>
        <tr>
            <td><c:out value="${benefitRaport.cost} zł"/></td>
            <td><c:out value="${benefitRaport.parts} zł"/></td>
            <td><c:out value="${benefitRaport.employee_salary} zł"/></td>
            <td><c:out value="${benefitRaport.time} h"/></td>
            <td><c:out value="${benefitRaport.benefit} zł"/></td>
        </tr>
    </table>
    <a href="http://localhost:8080/WarsztatSamochodowy/Raports"> Cofnij </a>
</c:if>
</br></br>
<%@include file="footer.jsp" %>
</body>
</html>
</html>
