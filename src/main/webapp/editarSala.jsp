<%@page import="com.asej.model.Partida"%>
<%@page import="com.asej.model.Sala"%>
<%@page import="com.asej.model.Suscriptor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
Sala sala = (Sala) request.getAttribute("sala");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar la Partida</title>
</head>
<body>

<h2>Editar Sala</h2>

<form action="editarSala" method="post">
    <input type="hidden" name="id_sala" value="<%= sala.getId_sala() %>">

    <label>Nombre:</label>
    <input type="text" name="nombre" maxlength="100" value="<%= sala.getNombre() %>">
    
    <label>Tipo:</label>
    <input type="text" name="tipo" maxlength="100" value="<%= sala.getTipo()%>">
</select>


    <button type="submit">Guardar Cambios</button>
</form>

<a href="index.jsp">Cancelar edicion</a>

</body>
</html>
