package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Sala;
import com.asej.model.Suscriptor;
import com.asej.model.Partida;
import com.asej.model.Cupon;
import com.asej.service.SalaService;
import com.asej.service.PartidaService;
import com.asej.service.CuponService;

@WebServlet("/iniciarOrganizar")
public class IniciarOrganizarPartidaController extends HttpServlet{
	
	SalaService salaService;
	PartidaService partidaService;
	CuponService cuponService;
	
	@Override
	public void init() throws ServletException {
		salaService = new SalaService();
		partidaService = new PartidaService();
		cuponService = new CuponService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partida = request.getParameter("partida");
		Suscriptor suscriptor = (Suscriptor) request.getSession().getAttribute("suscriptor");
		if (suscriptor != null) {
		    request.getSession().setAttribute("idSuscriptor", suscriptor.getId());
		}
		
//		if(usuario != null) {
//			//quiero obtener el usuario de la BD
//			Partida p = partidaService.getC(partida);
//			//quiero a�adir ese usuario como atributo a la request
//			request.setAttribute("usuario", u);
//		}
		
		List<Sala> salas = salaService.getSalas();
		request.getSession().setAttribute("salas", salas);;	
		System.out.println("las salas disponibles son: " + request.getSession().getAttribute("salas"));
		
		List<Cupon> cupones = cuponService.getCuponesDisponibles(suscriptor.getId());
		request.getSession().setAttribute("cupones", cupones);
		
		
//		request.getRequestDispatcher("/admin/organizarPartida.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/admin/organizarPartida.jsp");
	}

}
