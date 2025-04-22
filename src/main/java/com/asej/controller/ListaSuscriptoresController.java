package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Suscriptor;
import com.asej.service.CentroService;
import com.asej.service.SuscriptorService;

@WebServlet("/listaSuscriptores")
public class ListaSuscriptoresController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	SuscriptorService suscriptorService;
	CentroService centroService;
	
	@Override
	public void init() throws ServletException {
		suscriptorService = new SuscriptorService();
		centroService = new CentroService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Hacer consulta a BD para obtener todos los suscriptores
		List<Suscriptor> suscriptores = suscriptorService.getSuscriptores();
		//System.out.println(suscriptores);
		//2. Guardar en la request 
		request.getSession().setAttribute("suscriptores", suscriptores);
		//System.out.println("request: "+request.getSession().getAttribute("suscriptores"));
		//3. Redirigir a jsp
		response.sendRedirect("/bilboskp-asej/admin/superadmin/listaSuscriptores.jsp");
		
        
		
	}
}