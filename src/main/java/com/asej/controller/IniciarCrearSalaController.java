package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Sala;
import com.asej.service.SalaService;

@WebServlet("/iniciarCreacionSala")
public class IniciarCrearSalaController extends HttpServlet{
	//Enficado para el tipo de la sala, que creo que era para lo del si es centro o no, porpuesto
	SalaService salaService;
	
	@Override
	public void init() throws ServletException {
		salaService = new SalaService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sala = request.getParameter("sala");
		
//		if(usuario != null) {
//			//quiero obtener el usuario de la BD
//			Partida p = partidaService.getC(partida);
//			//quiero añadir ese usuario como atributo a la request
//			request.setAttribute("usuario", u);
//		}
		
		List<Sala> salas = salaService.getSalas();
		request.setAttribute("salas", salas);
		
		request.getRequestDispatcher("crearSala.jsp").forward(request, response);
	}

}
