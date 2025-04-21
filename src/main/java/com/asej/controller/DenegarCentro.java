package com.asej.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Centro;
import com.asej.model.Rol;
import com.asej.service.CentroService;
import com.asej.service.CuponService;
import com.asej.service.RolService;
import com.asej.service.SuscriptorService;

@WebServlet(name = "denegarCentro", urlPatterns = { "/denegarCentro" })
public class DenegarCentro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CentroService centroService; 
	SuscriptorService suscriptorService;
	RolService rolService;
	CuponService cuponService;
	
    public DenegarCentro() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		centroService = new CentroService();
		suscriptorService = new SuscriptorService();
		rolService = new RolService();
		cuponService = new CuponService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Guardar id_centro del post
		int id_centro = Integer.parseInt(request.getParameter("id_centro"));
		
		// 2 Obtener id_suscriptor
		int id_suscriptor = centroService.getCentroById(id_centro).getId();
		
		// 3 Eliminar centro
		if(centroService.deleteCentro(id_centro)) {
			
			// 4 Eliminar suscriptor asociado
			if(suscriptorService.deleteSuscriptor(id_suscriptor)) {
				
				// 5 Redirigir
				response.sendRedirect("/bilboskp-asej/centrosPendientes");
			} else {
				response.sendRedirect("/bilboskp-asej/admin/error.jsp");
			}
		} else {
			response.sendRedirect("/bilboskp-asej/admin/error.jsp");
		}
	}
}
