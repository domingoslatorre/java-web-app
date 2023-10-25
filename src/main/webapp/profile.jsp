<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="profile.title" /> ${usuario.nome}</title>
    <style>
        label, input {
            display: block;
        }
    </style>
</head>
<body>
<h1><fmt:message key="profile.title" /></h1>
<p><b><fmt:message key="profile.id" /></b> ${usuario.id}</p>
<p><b><fmt:message key="profile.title" /></b> ${usuario.nome}</p>
<p><b><fmt:message key="profile.email" /></b> ${usuario.email}</p>
</body>
</html>