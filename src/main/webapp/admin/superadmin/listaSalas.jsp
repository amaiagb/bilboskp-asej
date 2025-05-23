<%@ include file="/WEB-INF/includes/idioma.jsp" %>
<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Salas - BilboSKP</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

	
    
    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/admin/css/sb-admin-2.css" rel="stylesheet">

	<!-- Custom styles for this page -->
    <link href="${pageContext.request.contextPath}/admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <%@ include file="/WEB-INF/includes/sidebar_admin.jsp" %>
        <!-- End of Sidebar -->
       
        <div id="content-wrapper" class="d-flex flex-column">
           
            <div id="content">

                <!-- Topbar -->
                 <%@ include file="/WEB-INF/includes/topbar.jsp" %>
                <!-- End of Topbar -->
                
                <div class="container-fluid">
                    
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Salas de escape</h1>
                    </div>
                    
                        <!-- DataTables -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Listado de Salas</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                	<table class="table table-bordered" id="dataTable" width="90%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Tipo</th>
                                                <th>Estado</th>
                                                <th> </th>
                                            </tr>
                                        </thead>
                                       
                                        <tbody>
                                        <c:forEach items="${listaSalas}" var="sala">
                                        	<tr>
                                                <td>${sala.nombre}</td>
                                                <td>${sala.tipo}</td>
                                                <td>${sala.estado}</td>
                                                
                                                <c:choose>
											        <c:when test="${fn:toLowerCase(sala.estado) == 'habilitada'}">
												        <td >
												            <form action="/bilboskp-asej/inhabilitarSala" method="post">
		                                                		<input type="hidden" name="id_sala" value="${sala.id_sala }">
		                                                		<button type="submit" class="btn btn-danger  ">Deshabilitar</button>
		                                                	</form>
	                                                	</td>
											        </c:when>
											        <c:when test="${fn:toLowerCase(sala.estado) == 'deshabilitada'}">
											            <td >
												            <form action="/bilboskp-asej/habilitarSala" method="post">
		                                                		<input type="hidden" name="id_sala" value="${sala.id_sala }">
		                                                		<button type="submit" class="btn btn-info">Habilitar</button>
		                                                	</form>
	                                                	</td>
											        </c:when>
											    </c:choose>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
               
                </div> <!--  End container fluid -->
           
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
    <script src="/bilboskp-asej/admin/vendor/jquery/jquery.min.js"></script>
    <script src="/bilboskp-asej/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/bilboskp-asej/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/bilboskp-asej/admin/js/sb-admin-2.min.js"></script>
    
     <!-- Page level plugins -->
    <script src="/bilboskp-asej/admin/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/bilboskp-asej/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>
    
    <!-- Page level custom scripts -->
    <!-- 
    
     -->
	<script src="/bilboskp-asej/admin/js/demo/datatables-demo.js"></script>

</body>

</html>