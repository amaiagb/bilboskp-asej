<%@ include file="/WEB-INF/includes/idioma.jsp"%>
<%@page import="com.asej.model.Cupon"%>
<%@page import="com.asej.model.Suscriptor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<Cupon> cupones = (ArrayList<Cupon>) request.getSession().getAttribute("cupones");
%>
<%
Suscriptor suscriptor = (Suscriptor) request.getAttribute("suscriptor");
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title><fmt:message key="sidebar.comprar"/> - BilboSKP</title>

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
						<h1 class="h3 mb-0 text-gray-800"><fmt:message key="sidebar.comprar"/></h1>
					</div>
					<div>
						<!-- 
						<form action="/bilboskp-asej/comprarCupon" method="post"
							id="formComprarCupon">
							<input type="number" name="numeroCupones" id="cantidad" min="1"
								max="999" value="1" placeholder="Nº Cupones" required>
							<button type="submit">Comprar</button>
						</form>
						 -->
						<form action="/bilboskp-asej/comprarCupon" method="post"
							id="formComprarCupon" class="form-inline">
							<div class="form-group mr-2">
								<label for="cantidad" class="mr-2"><fmt:message key="cantidad"/></label> <input
									type="number" class="form-control" name="numeroCupones"
									id="cantidad" min="1" value="1" required>
							</div>

							<div class="form-group mr-2">
								<label for="precio" class="mr-2"><fmt:message key="precio"/> (€)</label>
								<c:if test="${tipoSuscriptor == 'centro'}">
									<input type="text" class="form-control" id="precio" value="0" readonly>
								</c:if>
								<c:if test="${tipoSuscriptor == 'suscriptor'}">
									<input type="text" class="form-control" id="precio" value="4.95" readonly>
								</c:if>
								

							</div>

							<div class="form-group mr-2">
								<label for="total" class="mr-2">Total (€)</label> <input
									type="text" class="form-control" id="total" readonly>
							</div>

							<button type="submit" class="btn btn-primary"><fmt:message key="comprar"/></button>
						</form>
					</div>

					<div class="row">
						<div class="col-xl-4 col-lg-5">
							<div class="card shadow mb-4"></div>
						</div>
					</div>

					<div class="row"></div>

					<footer class="sticky-footer bg-white">
						<div class="container my-auto">
							<div class="copyright text-center my-auto">
								<span>Copyright &copy; ASEJ 2025</span>
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
								<span aria-hidden="true">×</span>
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
$(function () {
    const $cantidad = $("#cantidad");
    const $precio = $("#precio");
    const $total = $("#total");

    function actualizarTotal() {
        const cantidad = parseInt($cantidad.val()) || 0;
        const precio = parseFloat($precio.val()) || 0;
        $total.val((cantidad * precio).toFixed(2));
    }

    $cantidad.on("input", actualizarTotal);
    actualizarTotal();
});
</script>
<script>
    window.onload = function() {
        const ahora = new Date();
        // Asegurarse de formatear con ceros a la izquierda
        const pad = num => String(num).padStart(2, '0');
        const fechaMin = ahora.getFullYear() + "-" + 
                         pad(ahora.getMonth()+1) + "-" +
                         pad(ahora.getDate()) + "T" +
                         pad(ahora.getHours()) + ":" +
                         pad(ahora.getMinutes());

        document.getElementById("fecha").min = fechaMin;
    }
</script>
</html>