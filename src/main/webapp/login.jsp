<%@ include file="/WEB-INF/includes/idioma.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title><fmt:message key="iniciarsesion"/> - BilboSKP</title>
    <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    <link rel="stylesheet" href="css/custom.css">
    <link rel="stylesheet" href="css/login.css">
    
</head>
<body class="no-scroll-shrink">
    <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top navbar-shrink" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="/bilboskp-asej/index.jsp"><img src="assets/img/logos/bilboSKP.png" alt="..." /></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <fmt:message key="menu"/>
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="/bilboskp-asej/index.jsp#portfolio"><fmt:message key="salasdeescape"/></a></li>
                        <li class="nav-item"><a class="nav-link" href="/bilboskp-asej/index.jsp#about"><fmt:message key="resenas"/></a></li>
  
                        <li class="nav-item"><a class="nav-link" href="/bilboskp-asej/login.jsp"><fmt:message key="iniciarsesion"/></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    <div class="login-container">
        <form action="login" method="POST" class="login-form">
            <h2><fmt:message key="iniciarsesion"/></h2>
           
            <div class="input-group">
                <label for="usuario"><fmt:message key="nombredeusuario"/></label>
                <input type="text"  name="usuario" required>
            </div>
            <div class="input-group">
                <label for="contrasena"><fmt:message key="contraseña"/></label>
                <input type="password" name="contrasena" required>
            </div>
            <button type="submit" class="login-btn"><fmt:message key="iniciarsesion"/></button>
            <div class="signup-link">
                <p class="link"><fmt:message key="login.nocuenta"/> <a href="registro.jsp"><fmt:message key="login.registrate"/></a></p>
                <p class="link"><fmt:message key="login.erescentro"/> <a href="registro-centro.jsp"><fmt:message key="login.registrate.centro"/></a></p>
                <c:choose>
			        <c:when test="${param.error == '1'}">
			            <p style="color:red;"><fmt:message key="login.mensaje.error1"/></p>
			        </c:when>
			        <c:when test="${param.error == '2'}">
			            <p style="color:red;"><fmt:message key="login.mensaje.error2"/></p>
			        </c:when>
			    </c:choose>
            </div>
        </form>
    </div>
<!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
