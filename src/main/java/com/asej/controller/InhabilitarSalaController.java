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

@WebServlet("/inhabilitarSala")
public class InhabilitarSalaController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	SalaService salaService;
	
	@Override
	public void init() throws ServletException {
		salaService = new SalaService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int salaId = Integer.parseInt(request.getParameter("id_sala"));
		
		/*
		try {
		    
		    
		} catch (NumberFormatException e) {
		    request.setAttribute("error majo", "ID de sala inválido, revisa");
		    request.getRequestDispatcher("listaSalas.jsp").forward(request, response);
		    return;
		}
		 */
		
		Sala s = new Sala();
		s.setId_sala(salaId);
		
		if(salaService.desactivarSala(s)) {
			System.out.println("sala inhabilitada magistralmente");
			response.sendRedirect("/bilboskp-asej/listaSalas");
			return;
		}else {
			List<Sala> listaSalas = salaService.getSalas();
			
			request.setAttribute("listaSalas", listaSalas);
			request.setAttribute("error", "la sala no se ha podido desactivar");
			
			request.getRequestDispatcher("listaSalas.jsp").forward(request, response);
		}
	}

}
