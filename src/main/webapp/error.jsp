<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="java.io.UnsupportedEncodingException"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - ¡Algo salió mal!</title>
</head>
<body>
    <div>
        <h1>¡Ups! Algo salió mal</h1>
        <p>Lo sentimos, hubo un problema en el proceso. Por favor, verifica los datos e intenta nuevamente.</p>

        <div>
            <p>Si el problema persiste, por favor contacta con el soporte técnico.</p>
        </div>

        <!-- Leer el parámetro 'from' de la URL para redirigir correctamente -->
        <%
            String fromPage = request.getParameter("from");
            if (fromPage == null) {
                fromPage = "index.jsp";  
            } else {
                try {
                    fromPage = URLDecoder.decode(fromPage, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    fromPage = "index.jsp"; 
                }
            }
        %>

        <a href="<%= fromPage %>" class="button">Intentar de nuevo</a>
        <br>
        <a href="index.jsp" class="button">Volver al inicio</a>
    </div>
</body>
</html>
