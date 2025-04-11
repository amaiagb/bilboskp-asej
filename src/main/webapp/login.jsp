<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Login - BilboSKP</title>
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
<body>
    <nav class="navbar navbar-expand-lg navbar-dark navbar-shrink fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="index.html"><img src="assets/img/logos/bilboSKP.png" alt="..." /></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="index.html#portfolio">Salas de Escape</a></li>
                        <li class="nav-item"><a class="nav-link" href="index.html#about">Reviews</a></li>
  
                        <li class="nav-item"><a class="nav-link" href="login.html">Iniciar Sesión</a></li>
                    </ul>
                </div>
            </div>
        </nav>
   
    <div class="login-container">
        <form action="login" method="POST" class="login-form">
            <h2>Iniciar sesión</h2>
           
            <div class="input-group">
                <label for="usuario">Nombre de Usuario</label>
                <input type="text"  name="usuario" required>
            </div>
            <div class="input-group">
                <label for="contrasena">Contraseña</label>
                <input type="password" name="contrasena" required>
            </div>
            <button type="submit" class="login-btn">Iniciar sesión</button>
            <div class="signup-link">
                <p class="link">¿No tienes cuenta? <a href="registro.jsp">Regístrate</a></p>
                <p class="link">¿Eres un centro? <a href="registro-centro.jsp">Regístrate como centro</a></p>
            </div>
        </form>
    </div>
</body>
</html>
