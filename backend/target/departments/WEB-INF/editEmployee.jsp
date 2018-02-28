<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Edit employee </title>

</head>
<body>

<h3>ADD/UPDATE EMPLOYEE</h3>
<a class="home" href="/"> <span>&#x2302;</span></a>

<div class="blocksEmployee" style="">


    <form action="addEmployee" method="post">

        <input type="hidden" name="id"
               value="${param['id'] eq null ? employee.id : param['id']}">

        <ul>
            <c:forEach items="${errorList['fullName']}" var="error">
                <li>${error}</li>
            </c:forEach>

        </ul>

        <input class="fields" type="text" name="fullName"
               value="<c:out value="${param['fullName'] eq null ?
               employee.fullName : param['fullName']}"/>"
               placeholder="Enter your name"><br>
        <ul>
            <c:forEach items="${errorList['email']}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>

        <input class="fields" type="text" name="email"
               value="<c:out value="${param['email'] eq null ? employee.email : param['email']}"/>"
               placeholder="Enter your email"><br>

        <ul>
            <c:forEach items="${errorList['birthday']}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>

        <input class="fields" type="date" name="birthday"

               value="${param['birthday'] eq null ? employee.birthday : param['birthday']}"
               placeholder="Enter your birthday"><br>

        <ul>
            <c:forEach items="${errorList['phoneNumber']}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>

        <input class="fields" type="text" name="phoneNumber"
               value="<c:out value="${param['phoneNumber'] eq null ? employee.phoneNumber : param['phoneNumber']}"/>"
               placeholder="Enter your phone number"><br>

        <ul>
            <c:forEach items="${errorList['salary']}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>

        <input class="fields" type="text" name="salary"
               value="<c:out value="${param['salary'] eq null ? employee.salary : param['salary']}"/>"
               placeholder="Enter salary"><br>
        <ul>
            <c:forEach items="${errorList['departmentId']}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>

        <select class=fields" name="departmentId"><br>


            <c:forEach items="${departments}" var="departments">
                <c:if test="${departments.id == employee.departmentId || param['id']==departments.id }">
                    <option value="<c:out value="${departments.id}"/>"> <c:out value="${departments.departmentName}"/> </option>
                </c:if>
            </c:forEach>


            <c:forEach items="${departments}" var="departments">
                <option value="<c:out value="${departments.id}"/>"> <c:out value="${departments.departmentName}"/>  </option>
            </c:forEach>
        </select><br>


        <c:if test="${employee.id ne null || param['id'] ne null}">
            <br> <input class="listButton" type="submit" value="Update employee" style="margin-left: 40%">
        </c:if>

        <c:if test="${employee.id eq null && param['id'] eq null}"><br>
            <br> <input class="listButton" type="submit" value="Add employee" style="margin-left: 40%">
        </c:if>
    </form>
</div>
</body>
</html>
