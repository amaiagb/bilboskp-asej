package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Partida;
import com.asej.model.Suscriptor;
import com.asej.service.PartidaService;

@WebServlet("/listaPartidas")
public class ListaPartidasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    PartidaService partidaService;
    
    @Override
    public void init() throws ServletException {
        partidaService = new PartidaService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Suscriptor s = (Suscriptor) (req.getSession().getAttribute("suscriptor"));
		int id_suscriptor = s.getId();
		
		String estadoParam = req.getParameter("estado"); 
		List<Partida> listaPartidas;
		
		if (estadoParam != null && !estadoParam.isEmpty()) {
		    listaPartidas = partidaService.getPartidasByIdYEstado(id_suscriptor, estadoParam);
		} else {
		    listaPartidas = partidaService.getPartidasById(id_suscriptor);
		}
		
		req.getSession().setAttribute("listaPartidas", listaPartidas);
		req.setAttribute("estadoSeleccionado", estadoParam);
		
		req.getRequestDispatcher("admin/listaPartidas.jsp").forward(req, resp);  
	}   
}

