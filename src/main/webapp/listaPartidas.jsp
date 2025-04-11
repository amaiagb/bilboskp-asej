<%@page import="com.asej.model.Partida"%>
<%@page import="com.asej.controller.ListaPartidasController" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% ArrayList<Partida> listaPartidas = (ArrayList<Partida>)request.getAttribute("listaPartidas"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/lista.css" />
</head>
<body>

<table>
		<tr>
			<th>Fecha</th>
			<th>Jugadores</th>
			<th>Descripcion</th>
			<th>estado</th>
			<th>Puntuacion</th>
		</tr>
		<%if(listaPartidas != null){ %>
			<%for(Partida p : listaPartidas){ %>
				<tr>
					<td><%= p.getFecha() %></td>
					<td><%= p.getJugadores() %></td>
					<td><%= p.getDescripcion() %></td>
					<td><%= p.getEstado() %></td>
					<td><%= p.getPuntuacion() %></td>
					
					
					<td>
						<form action="editarPartida" method="get">
							<input type="hidden" value="<%= p.getId_partida()%>" name="#">
							<button type="submit">Editar Partida</button>
						</form>
						
						<form action="eliminarPartida" method="post">
							<input type="hidden" value="<%= p.getId_partida()%>" name="#">
							<button type="submit">Borrar</button>
						</form>
					</td>
				</tr>
			<%} %>
		<%}else{ %>
			<tr>
				<td colspan="5">No hay productos</td>
			</tr>
		<%} %>
	</table>
	<a href="index.jsp">Volver</a>


</body>
</html>