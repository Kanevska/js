<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Departments </title>

</head>
<body>

<h3> DEPARTMENTS </h3>
<a class="home" href="/"><span>&#x2302;</span></a>
<form class="addFunction" method="get" action="getDepartmentForm">
    <input class="add" type="submit" value="+">
</form>

<c:forEach items="${departments}" var="departments">

        <div class="blocks">

            <table class="text">
                <tr>
                    <td>  <p class="name"><c:out value="${departments.departmentName}"/></p></td>
                    <td> <p class="address"> Address: <c:out value="${departments.address}"/></p></td>
                    <td> <p class="address"> Description: <c:out value="${departments.description}"/></p></td>
                </tr>
            </table>
            <div class="buttons">

                <form method="get" action="deleteDepartment">
                    <input type="hidden" name="id" value="${departments.id}">
                    <input class="deleteButton" type="submit" value="X">
                </form>


                <form method="get" action="editDepartment">
                    <input type="hidden" name="id" value="${departments.id}">
                    <input class="addButton" type="submit" value="edit">
                </form>

                <form method="get" action="employeeList">
                    <input type="hidden" name="id" value="${departments.id}">
                    <input class="listButton" type="submit" value="employee list">
                </form>
            </div>

        </div>
</c:forEach>

</body>
</html>