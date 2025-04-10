<%@page import="com.asej.model.Sala"%>
<%@page import="com.asej.controller.ListaSalasController" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% ArrayList<Sala> listaSalas = (ArrayList<Sala>)request.getAttribute("listaSalas"); %>
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
			<th>Id</th>
			<th>Nombre</th>
			<th>Tipo</th>
			<th>Estado</th>
		</tr>
		<%if(listaSalas != null){ %>
			<%for(Sala s : listaSalas){ %>
				<tr>
					<td><%= s.getId_sala() %></td>
					<td><%= s.getNombre() %></td>
					<td><%= s.getTipo() %></td>
					<td><%= s.getEstado() %></td>
				
					
					
					<td>
						<form action="cargarEditarSala" method="post">
    						<input type="hidden" value="<%= s.getId_sala()%>" name="id_sala">
    						<button type="submit">Editar Sala</button>
						</form>

						
						<form action="inhabilitarSala" method="post">
							<input type="hidden" value="<%= s.getId_sala()%>" name="sala">
							<button type="submit">Inhabilitar sala</button>
						</form>
					</td>
				</tr>
			<%} %>
		<%}else{ %>
			<tr>
				<td colspan="3">No hay Salas</td>
			</tr>
		<%} %>
	</table>
	<a href="index.jsp">Volver</a>


</body>
</html>