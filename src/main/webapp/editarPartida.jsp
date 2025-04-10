<%@page import="com.asej.model.Partida"%>
<%@page import="com.asej.model.Sala"%>
<%@page import="com.asej.model.Suscriptor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%ArrayList<Sala> salas = (ArrayList<Sala>) request.getAttribute("salas"); %>

<%
Partida partida = (Partida) request.getAttribute("partida");
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar la Partida</title>
</head>
<body>

<h2>Editar Partida</h2>

<form action="editarPartida" method="post">
    <input type="hidden" name="id_partida" value="<%= partida.getId_partida() %>">

    <label>Fecha:</label>
    <input type="datetime-local" name="date" value="<%= partida.getFecha().format(formatter) %>">

    <label>Jugadores:</label>
    <input type="number" name="jugadores" value="<%= partida.getJugadores() %>">

    <label>Descripción:</label>
    <input type="text" name="descripcion" maxlength="100" value="<%= partida.getDescripcion() %>">

    <label>Sala:</label>
   		<select id="sala" name="sala">
    	<% for(Sala sala : salas){ 
        	boolean selected = sala.getId_sala() == partida.getSala().getId_sala();
    	%>
        	<option value="<%= sala.getId_sala() %>" <%= selected ? "selected" : "" %>><%= sala.getNombre() %></option>
    	<%} %>
</select>


    <button type="submit">Guardar Cambios</button>
</form>

<a href="index.jsp">Cancelar edicion</a>

</body>
</html>
