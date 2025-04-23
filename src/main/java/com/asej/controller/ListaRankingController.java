package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Partida;
import com.asej.model.Sala;
import com.asej.model.Suscriptor;
import com.asej.service.PartidaService;
import com.asej.service.SalaService;

@WebServlet("/ranking")
public class ListaRankingController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	PartidaService partidaService;
	SalaService salaService;
	
	@Override
	public void init() throws ServletException {
		partidaService = new PartidaService();
		salaService = new SalaService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    Suscriptor s = (Suscriptor) req.getSession().getAttribute("suscriptor");
	    int id_suscriptor = s.getId();

	    String salaParam = req.getParameter("sala");
	    List<Partida> listaPartidas;
	    Integer salaSeleccionada = null;

	    if (salaParam != null && !salaParam.isEmpty()) {
	        int id_sala = Integer.parseInt(salaParam);
	        listaPartidas = partidaService.getPartidasByIdYSala(id_suscriptor, id_sala); // necesitas este método
	        salaSeleccionada = id_sala;
	    } else {
	        listaPartidas = partidaService.getPartidasById(id_suscriptor);
	    }

	    List<Sala> listaSalas = salaService.getSalas();

	    req.setAttribute("listaPartidas", listaPartidas);
	    req.setAttribute("listaSalas", listaSalas);
	    req.setAttribute("salaSeleccionada", salaSeleccionada);

	    req.getRequestDispatcher("admin/ranking.jsp").forward(req, resp); // mejor que redirect
	}

}