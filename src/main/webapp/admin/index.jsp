<%@ include file="/WEB-INF/includes/idioma.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../WEB-INF/includes/idioma.jsp" %>
<!DOCTYPE html>
<html lang="es">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<jsp:include page="/WEB-INF/includes/idioma.jsp"/>

<title><fmt:message key="inicio"/> - BilboSKP</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${pageContext.request.contextPath}/admin/css/sb-admin-2.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Navbar -->
	<!-- End of Navbar -->

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
			

			<!-- Content Wrapper -->
			<div id="content-wrapper" class="d-flex flex-column">

				<!-- Main Content -->
				<div id="content">

					<!-- Topbar -->
					<%@ include file="/WEB-INF/includes/topbar.jsp"%>
					<!-- End of Topbar -->

					<!-- Begin Page Content -->
					<div class="container-fluid">

						<!-- Page Heading -->
						<div
							class="d-sm-flex align-items-center justify-content-between mb-4">
							<h1 class="h3 mb-0 text-gray-800"><fmt:message key="inicio"/></h1>
						</div>

						<!-- Content Row -->
						<div class="row">

							<!-- Card 1 -->
							<div class="col-xl-3 col-md-6 mb-4">
								<div class="card border-left-primary shadow h-100 py-2">
									<div class="card-body">
										<div class="row no-gutters align-items-center">
											<div class="col mr-2">
												<div
													class="text-xs font-weight-bold text-primary+ text-uppercase mb-1">
													<fmt:message key="cupones.disponibles" /></div>
												<div class="h5 mb-0 font-weight-bold text-gray-800">${fn:length(cuponesDisponibles)}</div>
											</div>
											<div class="col-auto">
												<i class="fas fa-ticket-alt fa-2x text-gray-300"></i>
											</div>
										</div>
									</div>
								</div>
							</div>
							<c:if test="${tipoSuscriptor == 'centro'}">
								<!-- Card 2 -->
								<div class="col-xl-3 col-md-6 mb-4">
									<div class="card border-left-success shadow h-100 py-2">
										<div class="card-body">
											<div class="row no-gutters align-items-center">
												<div class="col mr-2">
													<div
														class="text-xs font-weight-bold text-success text-uppercase mb-1">
														<fmt:message key="partidas.jugadas"/></div>
													<div class="h5 mb-0 font-weight-bold text-gray-800">${fn:length(ultimasPartidas)}</div>
												</div>
												<div class="col-auto">
													<i class="fas fa-gamepad fa-2x text-gray-300"></i>
												</div>
											</div>
										</div>
									</div>
								</div>

								<!-- Card 3 -->
								<div class="col-xl-3 col-md-6 mb-4">
									<div class="card border-left-warning shadow h-100 py-2">
										<div class="card-body">
											<div class="row no-gutters align-items-center">
												<div class="col mr-2">
													<div
														class="text-xs font-weight-bold text-warning text-uppercase mb-1">
														<fmt:message key="partidas.pendientes"/></div>
													<div class="h5 mb-0 font-weight-bold text-gray-800">${numPartidasProgramadas}</div>
												</div>
												<div class="col-auto">
													<i class="fas fa-calendar fa-2x text-gray-300"></i>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</div>

						<!-- Content Row -->
						<div class="row mx-1">

							<!-- DataTables -->
							<div class="card shadow mb-4 col-xl-9 col-md-6">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary"><fmt:message key="admin.index.ultimaspartidas"/></h6>
								</div>
								<div class="card-body">
									<div class="table-responsive">
										<table class="table table-bordered" id="dataTable"
											width="100%" cellspacing="0">
											<thead>
												<tr>
													<c:if test="${tipoSuscriptor == 'centro'}">
														<th><fmt:message key="tabla.fecha"/></th>
														<th><fmt:message key="tabla.descripcion"/></th>
														<th><fmt:message key="tabla.jugadores"/></th>
													</c:if>
														<th><fmt:message key="tabla.puntuacion"/></th>
														<th><fmt:message key="tabla.sala"/></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${sessionScope.ultimasPartidas}"
													var="partida" varStatus="loop">
													<tr class="foreach">
														<c:if test="${tipoSuscriptor == 'centro'}">
															<td>${partida.fecha}</td>
															<td>${partida.descripcion}</td>
															<td>${partida.jugadores}</td>
														</c:if>
														<td>${partida.puntuacion}</td>
														<td>${partida.sala.nombre}</td>
													</tr>
												</c:forEach>

											</tbody>
										</table>
									</div>
								</div>
							</div>


						</div>

					</div>
					<!-- End Page Content -->
				</div>
				<!-- End of Main Content -->

				<!-- Footer -->
				<footer class="sticky-footer bg-white">
					<div class="container my-auto">
						<div class="copyright text-center my-auto">
							<span>Copyright &copy; ASEJ 2025</span>
						</div>
					</div>
				</footer>
				<!-- End of Footer -->

			</div>
			<!-- End of Content Wrapper -->
	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
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

</html>