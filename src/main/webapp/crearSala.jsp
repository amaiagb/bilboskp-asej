<%@page import="com.asej.model.Sala"%>
<%@page import="com.asej.model.Suscriptor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%Suscriptor suscriptor = (Suscriptor) request.getAttribute("suscriptor"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear salaa</title>
</head>
<body>
<div>
	<h2> crear sla</h2>
	<form action="crearSala" method="post">
		<input type="text" name="nombre" placeholder="nombre" maxlength="100" required>
		<input type="text" name="tipo" placeholder="tipo" maxlength="100" required>
		
		<button type="submit">Crear</button>
	</form>
	<a href="index.jsp">Volver</a>
</div>
</body>

</html>
