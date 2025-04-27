<%@ include file="/WEB-INF/includes/idioma.jsp"%>
<%@page import="com.asej.model.Cupon"%>
<%@page import="com.asej.model.Suscriptor"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
ArrayList<Cupon> listaCupones = (ArrayList<Cupon>) request.getSession().getAttribute("listaCupones");
%>
<%
Suscriptor suscriptor = (Suscriptor) request.getAttribute("suscriptor");
%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title><fmt:message key="cupones" /> - BilboSKP</title>

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


		<div id="content-wrapper" class="d-flex flex-column">

			<div id="content">

				<!-- Topbar -->
				<%@ include file="/WEB-INF/includes/topbar.jsp"%>
				<!-- End of Topbar -->

				<div class="container-fluid">

					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">
							<fmt:message key="cupones" />
						</h1>
					</div>
					<div>
						<!-- DataTables -->
						<div class="card shadow mb-4">
							<div class="card-header py-3">
								<h6 class="m-0 font-weight-bold text-primary">
									<fmt:message key="listado.cupones" />
								</h6>
							</div>
							<div class="card-body">
								<div class="table-responsive">
									<table class="table table-bordered" id="dataTable" width="90%"
										cellspacing="0">
										<thead>
											<tr>
												<th><fmt:message key="tabla.fecha.compra" /></th>
												<th><fmt:message key="tabla.fecha.caducidad" /></th>
												<th><fmt:message key="tabla.estado" /></th>
												<th><fmt:message key="precio" /></th>
												<th><fmt:message key="tabla.fecha.devolucion" /></th>
												<th></th>
											</tr>
										</thead>
										<%
										if (listaCupones != null) {
										%>
										<%
										for (Cupon c : listaCupones) {
										%>
										<tr>
											<td><%=c.getFecha_compra()%></td>
											<td><%=c.getFecha_caducidad()%></td>
											<td><%=c.getEstado()%></td>
											<td><%=c.getPrecio()%></td>
											<td>
												<%
												if (c.getFecha_devolucion() == null) {
												%> - <%
												} else {
												%> <%=c.getFecha_devolucion()%>
												<%
												}
												%>
											</td>
											<td>
												<%
												if (c.getEstado() != null && c.getEstado().trim().equalsIgnoreCase("disponible")) {
												%>
												<form action="../devolverCupon" method="post">
													<input type="hidden" value="<%=c.getId_cupon()%>"
														name="id_cupon">
													<button class="btn btn-primary" type="submit">
														<fmt:message key="devolver" />
													</button>
												</form> <%  } else {  
												%> <span class="text-muted"> </span> <% } %>
											</td>
										</tr>
										<%
										}
										%>
										<%
										} else {
										%>
										<tr>
											<td colspan="5"><fmt:message key="tabla.nocupones" /></td>
										</tr>
										<%
										}
										%>
									</table>
								</div>


								<div class="row"></div>



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
						<div class="modal fade" id="logoutModal" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
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

						<!-- Page level plugins -->
						<script
							src="/bilboskp-asej/admin/vendor/datatables/jquery.dataTables.min.js"></script>
						<script
							src="/bilboskp-asej/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

						<!-- Page level custom scripts -->
						<script src="/bilboskp-asej/admin/js/demo/chart-area-demo.js"></script>
						<script src="/bilboskp-asej/admin/js/demo/chart-pie-demo.js"></script>

						<script src="/bilboskp-asej/admin/js/demo/datatables-demo.js"></script>
</body>
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