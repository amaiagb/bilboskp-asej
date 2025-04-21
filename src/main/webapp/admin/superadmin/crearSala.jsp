<%@ include file="/WEB-INF/includes/idioma.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="es">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/x-icon"
	href="/bilboskp-asej/assets/favicon.ico">

<title>Nueva Sala - BilboSKP</title>

<!-- Custom fonts for this template-->
<link
	href="/bilboskp-asej/admin/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/bilboskp-asej/admin/css/sb-admin-2.css" rel="stylesheet">

</head>

<body id="page-top">

	<!-- Navbar -->
	<!-- End of Navbar -->

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<%@ include file="/WEB-INF/includes/sidebar_admin.jsp"%>
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
						<h1 class="h3 mb-0 text-gray-800">Crear nueva sala</h1>
					</div>

					<!-- Implementar if para mostrar una info cuando es centro o cuando es suscriptor -->
					<%-- 

                        --%>
					<!-- Content Row -->
					<div class="row mx-1">
						<div class="col-12 col-md-8 col-lg-6">
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Sala de
										escape</h6>
								</div>
								<div class="card-body">
									<div class="card-body ">

										<form action="/bilboskp-asej/crearSala" method="post">
											<div class="form-group row">
												<label for="nombre" class="col-sm-4 col-form-label">Nombre</label>
												<div class="col-sm-8">
													<input type="text" name="nombre" class="form-control"
														placeholder="Nombre de la sala">
												</div>
											</div>
											<div class="form-group row align-items-center">
												<label class="col-xl-4 col-form-label">Tipo</label>
												<div class="col-xl-8 d-flex align-items-center">
													<div class="form-check mr-3">
														<input class="form-check-input" type="radio" name="tipo"
															id="tipoCentro" value="centro" required> <label
															class="form-check-label" for="tipoCentro">Centro</label>
													</div>
													<div class="form-check">
														<input class="form-check-input" type="radio" name="tipo"
															id="tipoSuscriptor" value="suscriptor" required>
														<label class="form-check-label" for="tipoSuscriptor">Suscriptor</label>
													</div>
												</div>
											</div>

											<!-- Campo: Estado -->
											<div class="form-group row align-items-center">
												<label class="col-xl-4 col-form-label">Estado</label>
												<div class="col-xl-8 d-flex align-items-center">
													<div class="form-check mr-3">
														<input class="form-check-input" type="radio" name="estado"
															id="estadoHabilitada" value="habilitada" checked>
														<label class="form-check-label" for="estadoHabilitada">Habilitada</label>
													</div>
													<div class="form-check">
														<input class="form-check-input" type="radio" name="estado"
															id="estadoDeshabilitada" value="deshabilitada"> <label
															class="form-check-label" for="estadoDeshabilitada">Deshabilitada</label>
													</div>
												</div>
											</div>

											<!-- Botón Submit -->
											<div class="form-group row ">
												<div class="col-sm-4"></div>
												<div class="col-sm-8 text-left">
													<button type="submit" class="btn btn-primary mt-2">Crear
														Sala</button>
												</div>
											</div>
										</form>
									</div>
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
						<span>Copyright &copy; Your Website 2021</span>
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
						<span aria-hidden="true">Ã—</span>
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
	<script src="/bilboskp-asej/admin/vendor/jquery/jquery.min.js"></script>
	<script
		src="/bilboskp-asej/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="/bilboskp-asej/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/bilboskp-asej/admin/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="/bilboskp-asej/admin/vendor/chart.js/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="/bilboskp-asej/admin/js/demo/chart-area-demo.js"></script>
	<script src="/bilboskp-asej/admin/js/demo/chart-pie-demo.js"></script>

</body>

</html>