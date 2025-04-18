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
<body class="h-auto">
    <nav class="navbar navbar-expand-lg navbar-dark navbar-shrink fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="index.jsp"><img src="assets/img/logos/bilboSKP.png" alt="..." /></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="index.jsp#portfolio">Salas de Escape</a></li>
                        <li class="nav-item"><a class="nav-link" href="index.jsp#about">Reviews</a></li>
  
                        <li class="nav-item"><a class="nav-link" href="login.jsp">Iniciar Sesi�n</a></li>
                    </ul>
                </div>
            </div>
        </nav>
   
    <div class="login-container" id="registro-centro">
        <form action="registroCentro" method="POST" class="login-form">
            <h2>Registrar Centro</h2>
            
            <!-- Primer bloque: Datos del centro -->
            <div class="form-section">
                <div class="input-group">
                    <label for="nombre_centro">Nombre del Centro</label>
                    <input type="text" name="nombre_centro" required>
                </div>
                <div class="input-group">
                    <label for="localidad">Localidad</label>
                    <input type="text"  name="localidad" required>
                </div>
                <div class="input-group">
                    <label for="etapas_educativas">Etapas Educativas</label>
                    <input type="text"  name="etapas_educativas" required>
                </div>
                <div class="input-group">
                    <label for="num_alumnado">N�mero de Alumnado</label>
                    <input type="number" name="num_alumnado" required>
                </div>
            </div>
            <!-- Segundo bloque: Datos del personal de contacto -->
            <p class="espacio">Persona de Contacto</p>
            <div class="form-section">
                <div class="input-group">
                <label for="nombre">Nombre </label>
                <input type="text"  name="nombre" required>
            </div>
            <div class="input-group">
                <label for="usuario">Nombre Usuario</label>
                <input type="text" name="usuario" required>
            </div>
            <div class="input-group">
                <label for="email">Correo electr�nico</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="contrasena">Contrase�a</label>
                <input type="password"  name="contrasena" required>
            </div>
            </div>
            
            <button type="submit" class="login-btn">Registrar</button>
        </form>
    </div>

    <style>
        .form-section {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }
        
        .form-section .input-group {
            margin-bottom: 10px;
        }

        @media (max-width: 768px) {
            .form-section {
                grid-template-columns: 1fr; /* En pantallas más pequeñas, se convierte en una sola columna */
            }
        }
    </style>
</body>
</html>
