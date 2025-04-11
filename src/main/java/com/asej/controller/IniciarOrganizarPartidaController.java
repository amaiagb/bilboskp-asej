package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Sala;
import com.asej.model.Partida;
import com.asej.service.SalaService;
import com.asej.service.PartidaService;

@WebServlet("/iniciarOrganizarPartida")
public class IniciarOrganizarPartidaController extends HttpServlet{
	
	SalaService salaService;
	PartidaService partidaService;
	
	@Override
	public void init() throws ServletException {
		salaService = new SalaService();
		partidaService = new PartidaService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partida = request.getParameter("partida");
		
//		if(usuario != null) {
//			//quiero obtener el usuario de la BD
//			Partida p = partidaService.getC(partida);
//			//quiero a�adir ese usuario como atributo a la request
//			request.setAttribute("usuario", u);
//		}
		
		List<Sala> salas = salaService.getSalas();
		request.setAttribute("salas", salas);
		
		request.getRequestDispatcher("organizarPartida.jsp").forward(request, response);
	}

}
