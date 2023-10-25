<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        <fmt:message key="login.title" />
    </title>
    <style>
        label, input {
            display: block;
        }
    </style>
</head>
<body>
<h1>
    <fmt:message key="login.title" />
</h1>

<c:if test="${violations != null}">
<h3><fmt:message key="login.message" /></h2>
<ul>
    <c:forEach var="violation" items="${violations}">
    <li>${violation.propertyPath} ${violation.message}</li>
    </c:forEach>
</ul>
</c:if>

<form action="login" method="post">
    <label for="email"><fmt:message key="login.email" /></label>
    <input type="text" name="email" id="email" value="${email}">
    <label for="senha"><fmt:message key="login.password" /></label>
    <input type="password" name="senha" id="senha" value="${senha}">
    <input type="submit" value="<fmt:message key="login.button" />">
</form>

</body>
</html>