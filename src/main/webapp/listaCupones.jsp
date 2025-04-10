<%@page import="com.asej.model.Cupon"%>
<%@page import="com.asej.controller.ListaPartidasController" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <% ArrayList<Cupon> listaCupones = (ArrayList<Cupon>)request.getAttribute("listaCupones"); %>
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
			<th>Fecha de compra</th>
			<th>Fecha de caducidad</th>
			<th>Estado</th>
			<th>Precio</th>
			<th>Fecha de devolución</th>
		</tr>
		<%if(listaCupones != null){ %>
			<%for(Cupon c : listaCupones){ %>
				<tr>
					<td><%= c.getFecha_compra() %></td>
					<td><%= c.getFecha_caducidad() %></td>
					<td><%= c.getEstado() %></td>
					<td><%= c.getPrecio() %></td>
					<td><% if (c.getFecha_devolucion() == null) {%>
						No devuelto
					<%} else {%>
					<%= c.getFecha_devolucion() %>
					<% } %>
					</td>
					
					
					
					<td>
						<form action="devolverCupon" method="post">
							<input type="hidden" value="<%= c.getId_cupon()%>" name="id_cupon">
							<button type="submit">Devolver</button>
						</form>
					</td>
				</tr>
			<%} %>
		<%}else{ %>
			<tr>
				<td colspan="5">No hay cupones</td>
			</tr>
		<%} %>
	</table>
	<a href="index.jsp">Volver</a>


</body>
</html>