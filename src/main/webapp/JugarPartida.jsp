<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Descargar archivo ZIP</h2>

<form action="${pageContext.request.contextPath}/descargarZip" method="get">
    <button type="submit">Descargar ZIP</button>
</form>


<div>
	<ol>
		<li><a href="index.jsp"><button>volver al inicio</button></a></li>
		<li><a href="finalizarPartida.jsp"><button>"Finalizar partida"</button></a></li>
		
	</ol>
</div>
</body>
</html>