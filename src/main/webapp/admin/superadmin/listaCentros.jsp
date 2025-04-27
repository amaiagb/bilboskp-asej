<%@ include file="/WEB-INF/includes/idioma.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Centros - BilboSKP</title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath}/admin/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">



<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/admin/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom styles for this page -->
<link
	href="${pageContext.request.contextPath}/admin/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<!-- Bootstrap core JavaScript-->
<script src="/bilboskp-asej/admin/vendor/jquery/jquery.min.js"></script>
<script
	src="/bilboskp-asej/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<%@ include file="/WEB-INF/includes/sidebar_admin.jsp"%>
		<!-- End of Sidebar -->

		<div id="content-wrapper" class="d-flex flex-column">

			<div id="content">

				<!-- Topbar -->
				<%@ include file="/WEB-INF/includes/topbar.jsp"%>
				<!-- End of Topbar -->

				<div class="container-fluid">

					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Centros educativos
							registrados</h1>
					</div>



					<!-- DataTables -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">Listado de
								Centros</h6>
						</div>
						<div class="card-body">
							<p>
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Nombre</th>
											<th>Localidad</th>
											<th>Etapas Educativas</th>
											<th>Número Alumnado</th>
											<th>Fecha</th>
											<th>Estado</th>
											<th>Contacto</th>
											<th>Email</th>
											<th></th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Nombre</th>
											<th>Localidad</th>
											<th>Etapas Educativas</th>
											<th>Número Alumnado</th>
											<th>Fecha</th>
											<th>Estado</th>
											<th>Contacto</th>
											<th>Email</th>
											<th></th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach items="${centros}" var="centro">
											<tr>
												<td>${centro.nombre_centro}</td>
												<td>${centro.localidad}</td>
												<td>${centro.etapas_educativas}</td>
												<td>${centro.num_alumnado}</td>
												<td>${centro.fecha_alta}</td>
												<td>${centro.estado}</td>
												<td>${centro.nombre}</td>
												<td>${centro.email}</td>

												<c:choose>
													<c:when test="${centro.estado == 'aceptado'}">
														<td>
															<form action="/bilboskp-asej/cancelarCentro"
																method="post">
																<input type="hidden" name="id_centro"
																	value="${centro.id_centro }">
																<button type="submit" class="btn btn-danger tx-dark">Dar
																	de baja</button>
															</form>
														</td>
													</c:when>
													<c:when test="${centro.estado == 'pendiente'}">
														<td>
															<form action="/bilboskp-asej/aprobarCentro" method="post"
																class="d-inline">
																<input type="hidden" name="origen" value="lista">
																<input type="hidden" name="id_centro"
																	value="${centro.id_centro }">
																<button type="submit" class="btn btn-info">Aprobar</button>
															</form>
															<form action="/bilboskp-asej/denegarCentro" method="post"
																class="d-inline">
																<input type="hidden" name="origen" value="lista">
																<input type="hidden" name="id_centro"
																	value="${centro.id_centro }">
																<button type="submit" class="btn btn-secondary tx-dark">Denegar</button>
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

				</div>
				<!--  End container fluid -->

				<footer class="sticky-footer bg-white">
					<div class="container my-auto">
						<div class="copyright text-center my-auto">
							<span>Copyright &copy; Your Website 2021</span>
						</div>
					</div>
				</footer>
			</div>
		</div>

		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fas fa-angle-up"></i>
		</a>

		<!-- Logout Modal-->
		<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Ready to
							Leave?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">Ã</span>
						</button>
					</div>
					<div class="modal-body">Select "Logout" below if you are
						ready to end your current session.</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancel</button>
						<a class="btn btn-primary" href="login.html">Logout</a>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal de Reembolso -->
		<div class="modal fade" id="reembolsoModal" tabindex="-1"
			role="dialog" aria-labelledby="reembolsoModalLabel"
			aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="reembolsoModalLabel">Reembolso</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">

						<c:if test="${reembolso}">
							<p>
								Se ha realizado un reembolso a <strong>${email}</strong>.
							</p>
							<p>
								Por un valor de: <strong>${totalReembolso} &#8364;</strong>.
							</p>
						</c:if>
					</div>
					<div class="modal-footer">
		                
		                <a href="listaCentros" class="btn btn-secondary" >Cerrar</a>
		            </div>
		        </div>
		    </div>
		</div>
</div>

	
		<c:if test="${not empty requestScope.reembolso}">
			<script>
				$(document).ready(function() {
					$('#reembolsoModal').modal('show');
				});
			</script>
		</c:if>



		<!-- Core plugin JavaScript-->
		<script
			src="/bilboskp-asej/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

		<!-- Custom scripts for all pages-->
		<script src="/bilboskp-asej/admin/js/sb-admin-2.min.js"></script>

		<!-- Page level plugins -->
		<script
			src="/bilboskp-asej/admin/vendor/datatables/jquery.dataTables.min.js"></script>
		<script
			src="/bilboskp-asej/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

		<!-- Page level custom scripts -->
		<!-- 
    
     -->
		<script src="/bilboskp-asej/admin/js/demo/datatables-demo.js"></script>
</body>

</html>