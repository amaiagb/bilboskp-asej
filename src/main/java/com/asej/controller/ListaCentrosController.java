package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Centro;
import com.asej.service.CentroService;
import com.asej.service.PartidaService;

@WebServlet("/listaCentros")
public class ListaCentrosController extends HttpServlet {
private static final long serialVersionUID = 1L;
	PartidaService partidaService;
	CentroService centroService;
	
	@Override
	public void init() throws ServletException {
		partidaService = new PartidaService();
		centroService = new CentroService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Hacer consulta a BD centros en estado 'pendiente'
		List<Centro> centros = centroService.getCentros();
		
		//2. Guardar en la request 
		request.getSession().setAttribute("centros", centros);
		
		//3. Redirigir a jsp
		response.sendRedirect("/bilboskp-asej/admin/superadmin/listaCentros.jsp");
		
        
		
	}
}