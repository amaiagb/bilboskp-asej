<%@ include file="/WEB-INF/includes/idioma.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
<<<<<<< Updated upstream
<meta charset="ISO-8859-1">
<title>Inicio</title>
</head>
<body>
	<a href="login.jsp">Login</a>
	<a href="registro.jsp"><fmt:message key="registro" /></a></br></br>
	
	 <a href="?lang=es">Español</a> |
     <a href="?lang=en">English</a>
=======
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compra de Cupones</title>
</head>
<body>
    <h1>Compra de Cupones</h1>

    <!-- Formulario para elegir el número de cupones -->
    <form action="comprarCupon" method="post">
        <label for="numeroCupones">Selecciona el número de cupones a comprar:</label><br>
        <input type="number" id="numeroCupones" name="numeroCupones" min="1" value="1" required><br><br>
        <label for="rol">Selecciona el rol:</label><br>
        <select id="rol" name="rol" required>
            <option value="suscriptor">Suscriptor</option>
            <option value="centro">Centro</option>
        </select><br><br>
        <input type="submit" value="Comprar">
    </form>
>>>>>>> Stashed changes
</body>
</html>

