<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="css/styles.css">
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
                        <li class="nav-item"><a class="nav-link" href="index.jsp#portfolio">Salas de Escape</a></li>
                        <li class="nav-item"><a class="nav-link" href="index.jsp#about">Reviews</a></li>
  
                        <li class="nav-item"><a class="nav-link" href="login.jsp">Iniciar Sesión</a></li>
                    </ul>
                </div>
            </div>
        </nav>
   
    <div class="login-container">
        <form action="registro" method="POST" class="login-form">
            <h2>Registrar</h2>
            <div class="input-group">
                <label for="nombre">Nombre </label>
                <input type="text"  name="nombre" required>
            </div>
            <div class="input-group">
                <label for="usuario">Nombre Usuario</label>
                <input type="text" name="usuario" required>
            </div>
            <div class="input-group">
                <label for="email">Correo electrónico</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="contrasena">Contraseña</label>
                <input type="password"  name="contrasena" required>
            </div>
            
            <button type="submit" class="login-btn">Registrar</button>
            
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
