<%@ include file="/WEB-INF/includes/idioma.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Partidas - BilboSKP</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
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
                        <h1 class="h3 mb-0 text-gray-800"><fmt:message key="sidebar.listaP"/></h1>
                        <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
                            <i class="fas fa-download fa-sm text-white-50"></i> Report<fmt:message key="reportar"/></a>
                    </div>
                    
                    <div class="row">
                        <!-- DataTables -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary"><fmt:message key="historial.partidas"/></h6>
                            </div>
                            <div class="card-body">
                             <form method="get" action="/bilboskp-asej/listaPartidas" class="mb-4">
								<label for="partida">Filtrar por estado:</label>
								<select name="estado" id="estado" class="form-control w-25 d-inline mx-2">
								    <option value="">Todas las partidas</option>
								    <option value="programada" ${param.estado == 'programada' ? 'selected' : ''}>Programada</option>
								    <option value="finalizada" ${param.estado == 'finalizada' ? 'selected' : ''}>Finalizada</option>
								</select>

								    <button type="submit" class="btn btn-primary">Filtrar</button>
								</form>
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th><fmt:message key="tabla.fecha"/></th>
                                                <th><fmt:message key="tabla.descripcion"/></th>
                                                <th><fmt:message key="tabla.jugadores"/></th>
                                                <th><fmt:message key="tabla.estado"/></th>
                                                <th><fmt:message key="tabla.sala"/></th>
                                            </tr>
                                        </thead>
                                        
                                        <tbody>
                                        <c:forEach items="${listaPartidas}" var="partida">
                                        	<tr>
                                                <td>${partida.fecha}</td>
                                                <td>${partida.descripcion}</td>
                                                <td>${partida.jugadores}</td>
                                                <td>${partida.estado}</td>
                                                <td>${partida.sala.nombre}</td>
                                                <td>
	                                                <form action="/bilboskp-asej/borrarPartida" method="post">
														<input type="hidden" value="${partida.id_partida}" name="partida">
														<button type="submit"><fmt:message key="borrar"/></button>
													</form>
												</td>
                                            </tr>
                                        </c:forEach>
                                            
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div> <!-- end row -->
               
                </div> <!--  End container fluid -->
           
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; ASEJ 2025</span>
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
    <script src="${pageContext.request.contextPath}/admin/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/admin/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath}/admin/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath}/admin/js/demo/chart-area-demo.js"></script>
    <script src="${pageContext.request.contextPath}/admin/js/demo/chart-pie-demo.js"></script>

</body>

</html>