<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="registration.title" /></title>
    <style>
        label, input {
            display: block;
        }
    </style>
</head>
<body>
<h1><fmt:message key="registration.title" /></h1>

<c:if test="${violations != null}">
<h3><fmt:message key="registration.message" /></h2>
<ul>
    <c:forEach var="violation" items="${violations}">
    <li>${violation.propertyPath} ${violation.message}</li>
    </c:forEach>
</ul>
</c:if>

<c:if test="${existeErro != null}">
<p>${existeErro}</p>
</c:if>

<form action="cadastro" method="post">
    <label for="nome"><fmt:message key="registration.name" /></label>
    <input type="text" name="nome" id="nome" value="${cadastroForm.nome}">
    
    <label for="email"><fmt:message key="registration.email" /></label>
    <input type="text" name="email" id="email" value="${cadastroForm.email}">
    
    <label for="senha"><fmt:message key="registration.password" /></label>
    
    <input type="password" name="senha" id="senha" value="${cadastroForm.senha}">
    <input type="submit" value="<fmt:message key="registration.button" />">
</form>

</body>
</html>