package com.asej.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.asej.model.Partida;
import com.asej.model.Suscriptor;
import com.asej.service.CuponService;
import com.asej.service.PartidaService;

@WebServlet("/mirarCodigo")
public class MirarCodigoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PartidaService partidaService;
	private CuponService cuponService;

	public MirarCodigoController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		partidaService = new PartidaService();
		cuponService = new CuponService();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");

		if (codigo == null || codigo.trim().isEmpty()) {
			response.sendRedirect("login.jsp?error=1");
			return;
		}

		Partida partida = partidaService.mirarCodigo(codigo);
		System.out.println("Partida encontrada: " + partida);

		if (partida != null) {
			HttpSession session = request.getSession();
			session.setAttribute("partida", partida);
			cuponService.setCuponEnUso();
			
			response.sendRedirect("admin/jugarPartida.jsp");
		} else {
			response.sendRedirect("login.jsp?error=1");
		}
	}
}
