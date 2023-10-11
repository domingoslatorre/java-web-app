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

<c:if test="${erros != null}">
<h3>Erros no formulário</h2>
<ul>
    <c:forEach var="erro" items="${erros}">
    <li>${erro}</li>
    </c:forEach>
</ul>
</c:if>

<form action="login" method="post">
    <label for="email">E-mail</label>
    <input type="email" name="email" id="email" value="${email}">
    <label for="senha">Senha</label>
    <input type="password" name="senha" id="senha" value="${senha}">
    <input type="submit" value="Entrar">
</form>

</body>
</html>