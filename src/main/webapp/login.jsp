<%@ include file="/WEB-INF/includes/idioma.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - BilboSKP</title>
</head>
<body>
	<form action="login" method="post" class="login">
		<input type="text" name="usuario" placeholder="Nombre de Usuario">
		<input type="password" name="contrasena" placeholder="Contraseña">
		<button type="submit" class="btn">Entrar</button>
	</form>
</body>
</html>