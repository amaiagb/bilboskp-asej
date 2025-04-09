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

@WebServlet("/listaPartidas")
public class ListaPartidasController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	PartidaService partidaService;
	
	@Override
	public void init() throws ServletException {
		partidaService = new PartidaService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Partida> listaPartidas = partidaService.getPartidas();
		
		req.setAttribute("listaPartidas", listaPartidas);
		System.out.println(listaPartidas);
		
//        if (req.getSession().getAttribute("usuario") != null) {
//            req.getRequestDispatcher("/private/editarProducto.jsp").forward(req, resp);
//        } else {
            req.getRequestDispatcher("listaPartidas.jsp").forward(req, resp);
//        }
        
		
	}
}