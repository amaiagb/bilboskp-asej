package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Partida;
import com.asej.service.PartidaService;

@WebServlet("/borrarPartida")
public class BorrarPartidaController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	PartidaService partidaService;
	
	@Override
	public void init() throws ServletException {
		partidaService = new PartidaService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int partidaId;

		try {
		    partidaId = Integer.parseInt(request.getParameter("partida"));
		} catch (NumberFormatException e) {
		    request.setAttribute("error brother", "the ID de partida inválido, revisa");
		    request.getRequestDispatcher("listaPartidas.jsp").forward(request, response);
		    return;
		}

		
		Partida p = new Partida();
		
		p.setId_partida(partidaId);
		
		if(partidaService.deletePartida(p)) {
			System.out.println("partida perfectamente deleteada");
			response.sendRedirect("listaPartidas");
		}else {
			List<Partida> listaPartidas = partidaService.getPartidas();
			
			request.setAttribute("listaPartidas", listaPartidas);
			request.setAttribute("error", "El producto no ha podido ser borrado");
			
			request.getRequestDispatcher("listaPartidas.jsp").forward(request, response);
		}
	}

}
