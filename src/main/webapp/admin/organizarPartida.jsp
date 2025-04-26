<%@ include file="/WEB-INF/includes/idioma.jsp" %>
<%@page import="com.asej.model.Sala"%>
<%@page import="com.asej.model.Cupon"%>
<%@page import="com.asej.model.Suscriptor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%ArrayList<Sala> salas = (ArrayList<Sala>) request.getSession().getAttribute("salas"); %>
<%ArrayList<Cupon> cupones = (ArrayList<Cupon>) request.getSession().getAttribute("cupones"); %>
<%Suscriptor suscriptor = (Suscriptor) request.getAttribute("suscriptor"); %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Organizar partida - BilboSKP</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/admin/css/sb-admin-2.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
		<c:if test="${tipoSuscriptor == 'centro'}">
			<%@ include file="/WEB-INF/includes/sidebar.jsp"%>
		</c:if>
		<c:if test="${tipoSuscriptor == 'suscriptor'}">
			<%@ include file="/WEB-INF/includes/sidebarSuscriptor.jsp"%>
		</c:if>
		<!-- End of Sidebar -->
		
		
        <div id="content-wrapper" class="d-flex flex-column">

           
            <div id="content">

                <!-- Topbar -->
                 <%@ include file="/WEB-INF/includes/topbar.jsp" %>
                <!-- End of Topbar -->
                
                <div class="container-fluid">

                    
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Organizar Partida</h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                                class="fas fa-download fa-sm text-white-50"></i> Reportar</a>
                    </div>
                    <div>
						<form action="/bilboskp-asej/organizar" method="post">
							<input type="datetime-local" name="date" id="fecha" placeholder="Fecha y Hora" required>
							<input type="number" name="jugadores" id="cantidad" min="1" max="<%= cupones.size() %>" value="1" placeholder="Nº Jugadores" required>
							<input type="text" name="descripcion" placeholder="Descripción" maxlength="100">
							<select id="sala" name="sala">
							<% for(Sala sala : salas){ %>
								<option value="<%= sala.getId_sala() %>"><%= sala.getNombre() %></option>
							<%} %>
							</select>
							<button type="submit">Crear</button>
						</form>
						<div>
						    <c:if test="${not empty errorFecha}">
						        ${errorFecha}
						    </c:if>
						</div>
						
					</div>

                    
                    <div class="row">
                    

                      
                    </div>

                    

                    <div class="row">

                        

                       
                        <div class="col-xl-4 col-lg-5">
                            <div class="card shadow mb-4">
                                
                            </div>
                        </div>
                    </div>

                   
                    <div class="row">

                       

                      
                

            </div>
           
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            
        </div>
        

    </div>
    
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>

</body>
<script>
    window.onload = function() {
        const ahora = new Date();
        const pad = num => String(num).padStart(2, '0');
        const fechaFormateada = "D:" + ahora.getFullYear() + "-" +
                                pad(ahora.getMonth() + 1) + "-" +
                                pad(ahora.getDate()) + " H:" +
                                pad(ahora.getHours()) + ":" +
                                pad(ahora.getMinutes());

        document.getElementById("fecha").value = fechaFormateada;
    }
</script>
</html>