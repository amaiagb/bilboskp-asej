<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Compra Confirmada</title>
</head>
<body>
    <h1>Compra Confirmada</h1>

    <% 
        // Recuperamos el número de cupones del formulario
        String numeroCuponesStr = request.getParameter("numeroCupones");
        int numeroCupones = Integer.parseInt(numeroCuponesStr);

        // Muestra el número de cupones comprados
    %>
    <p>Has comprado <%= numeroCupones %> cupones.</p>
    
    <a href="index.jsp">Volver a index</a>
</body>
</html>