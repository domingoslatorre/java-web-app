<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        label, input {
            display: block;
        }
    </style>
</head>
<body>
<h1>Login</h1>

<c:if test="${violations != null}">
<h3><fmt:message key="registration.message" /></h2>
<ul>
    <c:forEach var="violation" items="${violations}">
    <li>${violation.propertyPath} ${violation.message}</li>
    </c:forEach>
</ul>
</c:if>

<c:if test="${loginError != null}">
<p>${loginError}</p>
</c:if>

<form action="login" method="post">
    <label for="email">E-mail</label>
    <input type="email" name="email" id="email" value="${loginForm.email}">
    <label for="senha">Senha</label>
    <input type="password" name="senha" id="senha" value="${loginForm.senha}">
    <input type="submit" value="Entrar">
</form>

</body>
</html>