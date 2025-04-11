<%@ include file="/WEB-INF/includes/idioma.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Inicio</title>
</head>

<body>
	<a href="login.jsp">Login</a>
	<a href="registro.jsp"><fmt:message key="registro" /></a></br></br>
	
	 <a href="?lang=es">Español</a> |
     <a href="?lang=en">English</a>
     
     <aside>
	<div>
		<ol>
			<li><a href="iniciarOrganizarPartida"><button>organizar una partida</button></a></li>
			<li><a href="listaPartidas"><button>Ver partidas</button></a></li>
			<li><a href="JugarPartida.jsp"><button>jugar partida</button></a></li>
			<br>
			<br>
			<li><a href="crearSala.jsp"><button>Crear sala</button></a></li>
			<li><a href="listaSalas"><button>Lista sala</button></a></li>
			<br>
			<li><a href="login.jsp"><button>Login Adminisrador</button></a></li>
			<li><a><button>tombola</button></a></li>
		</ol>
	</div>
</aside>


    <h1>Compra de Cupones</h1>

    <!-- Formulario para elegir el número de cupones -->
    <form action="comprarCupon" method="post">
        <label for="numeroCupones">Selecciona el número de cupones a comprar:</label><br>
        <input type="number" id="numeroCupones" name="numeroCupones" min="1" value="1" required><br><br>
        <label for="rol">Selecciona el rol: (Implementarlo en sesión)</label><br>
        <select id="rol" name="rol" required>
            <option value="suscriptor">Suscriptor</option>
            <option value="centro">Centro</option>
        </select><br><br>
        <label for="id_suscriptor">Id del suscriptor: (Implementarlo en sesión):</label><br>
        <input type="number" id="id_suscriptor" name="id_suscriptor" min="1" required><br><br>
        <input type="submit" value="Comprar">
    </form>

</body>
</html>

