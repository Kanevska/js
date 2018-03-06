<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Edit department </title>

</head>
<body>

<h3>ADD/UPDATE DEPARTMENT</h3>
<a class="home" href="/"><span>&#x2302;</span></a>


<div class="departmentBock">


    <form action="addDepartment" method="post">


        <input class="fields" type="hidden" name="id"
               value="<c:out value="${param['id'] eq null ? department.id : param['id']}"/>">

        <ul>
            <c:forEach items="${errorList['departmentName']}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>

        <input class="fields" type="text" name="departmentName"

               value="<c:out value="${param['departmentName'] eq null ? department.departmentName : param['departmentName']}"/>"
               placeholder="Enter department name">

        <ul>
            <c:forEach items="${errorList['description']}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>

        <input class="fields" type="text" name="description"
               value="<c:out value="${param['description'] eq null ? department.description: param['description']}"/>"
               placeholder="Enter department description">


        <ul>
            <c:forEach items="${errorList['address']}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>


        <input class="fields" type="text" name="address"
               value="<c:out value="${param['address'] eq null ? department.address: param['address']}"/>"
               placeholder="Enter department address">

        <c:if test="${department.id ne null || param['id'] ne null }">

            <input class="listButton" type="submit" value="Update department" style="margin-left: 40%">
        </c:if>

        <c:if test="${department.id eq null && param['id'] eq null}">
            <input class="listButton" type="submit" value="Add department" style="margin-left: 40%">
        </c:if>
    </form>
</div>
</body>
</html>
