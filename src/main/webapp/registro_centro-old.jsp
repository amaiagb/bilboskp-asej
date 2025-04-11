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
	<h2>Nueva Suscripci�n</h2>
	<a href="registro-centro.jsp">Soy Centro Educativo</a>
	<form action="registroCentro" method="post" class="">
		<fieldset>
			<legend>Persona de Contacto</legend>
			<input type="text" name="nombre" placeholder="Nombre">
			<input type="text" name="usuario" placeholder="Nombre de Usuario">
			<input type="password" name="contrasena" placeholder="Contrase�a">
			<input type="email" name="email" placeholder="Correo Electr�nico">	
		</fieldset>	
		<input type="text" name="nombre_centro" placeholder="Nombre del Centro">
		<input type="text" name="localidad" placeholder="Localidad">
		<input type="text" name="etapas_educativas" placeholder="Etapas educativas">
		<input type="text" name="num_alumnado" placeholder="N�mero de alumnado">
		
		<button type="submit" class="btn">Entrar</button>
	</form>
</body>
</html>