<%@ include file="/WEB-INF/includes/idioma.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro Suscriptor</title>
</head>
<body>
	<h2><fmt:message key="registro.header.title" /></h2>
	<a href="registro_centro.jsp">Soy Centro Educativo</a>
	<form action="registro" method="post" class="">
		<input type="text" name="nombre" placeholder="Nombre">
		<input type="text" name="usuario" placeholder="Nombre de Usuario">
		<input type="password" name="contrasena" placeholder="Contraseña">
		<input type="email" name="email" placeholder="Correo Electrónico">		
		<button type="submit" class="btn">Entrar</button>
	</form>
</body>
</html>