<%@page import="com.asej.model.Sala"%>
<%@page import="com.asej.model.Suscriptor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%ArrayList<Sala> salas = (ArrayList<Sala>) request.getAttribute("salas"); %>
<%Suscriptor suscriptor = (Suscriptor) request.getAttribute("suscriptor"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Organizar partida</title>
</head>
<body>
<div>
	<h2>Organizar partida</h2>
	<form action="organizar" method="post">
<input type="datetime-local" name="date" id="fecha" placeholder="Fecha y Hora" required>
<input type="number" name="jugadores" id="cantidad" min="1" max="999" value="1" placeholder="Nº Jugadores" required>
<input type="text" name="descripcion" placeholder="Descripción" maxlength="100">
		<select id="sala" name="sala">
			<% for(Sala sala : salas){ %>
				<option value="<%= sala.getId_sala() %>"><%= sala.getNombre() %></option>
			<%} %>
		</select>
		
		<button type="submit">Crear</button>
	</form>
	<a href="index.jsp">Volver</a>
</div>
</body>
<script>
    window.onload = function() {
        const ahora = new Date();
        // Asegurarse de formatear con ceros a la izquierda
        const pad = num => String(num).padStart(2, '0');
        const fechaMin = ahora.getFullYear() + "-" + 
                         pad(ahora.getMonth()+1) + "-" +
                         pad(ahora.getDate()) + "T" +
                         pad(ahora.getHours()) + ":" +
                         pad(ahora.getMinutes());

        document.getElementById("fecha").min = fechaMin;
    }
</script>

</html>
