<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <link rel="stylesheet" href="css/style.css">
    <title>Employee list </title>

</head>
<body>

<h3>EMPLOYEE LIST OF ${dep.departmentName} DEPARTMENT</h3>
<a class = "home" href="/"> <span>&#x2302;</span></a>

<form class="addFunction" method="get" action="getEmployeeForm">
    <input class="add" type="submit" value="+">
</form>
<c:forEach items="${employees}" var="employee">

    <div class="blocks">
        <table class="text" style="width:85%">
            <tr>
                <td><p class="name"><c:out value="${employee.fullName}"/></p></td>
                <td><p class="address"> email:<c:out value=" ${employee. email}"/></p></td>
                <td><p class="address">birthday:<c:out value="${employee. birthday}"/></p></td>
                <td><p class="address">salary: <c:out value="${employee. salary}"/></p></td>
                <td><p class="address"> number: <c:out value="${employee. phoneNumber}"/></p></td>
            </tr>
        </table>
        <div class="buttons" style="width:14%">
            <form method="get" action="deleteEmployee">
                <input type="hidden" name="id" value="${employee.id}">
                <input class="deleteButton" type="submit" value="X">
            </form>

            <form method="get" action="editEmployee">
                <input type="hidden" name="id" value="${employee.id}">
                <input class="addButton" type="submit" value="edit">
            </form>
        </div>

    </div>
</c:forEach>
</body>
</html>
