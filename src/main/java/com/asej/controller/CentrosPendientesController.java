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

@WebServlet(name = "centrosPendientes", urlPatterns = { "/centrosPendientes" })
public class CentrosPendientesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CentroService centroService; 
	SuscriptorService suscriptorService;
	RolService rolService;
	CuponService cuponService;
	
    public CentrosPendientesController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		centroService = new CentroService();
		suscriptorService = new SuscriptorService();
		rolService = new RolService();
		cuponService = new CuponService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. Hacer consulta a BD centros en estado 'pendiente'
		List<Centro> centrosPendientes = centroService.getCentrosPendientes();
		
		//2. Guardar en la request 
		request.getSession().setAttribute("centrosPendientes", centrosPendientes);
		
		//3. Redirigir a jsp
		response.sendRedirect("/bilboskp-asej/admin/superadmin/listaCentrosPendientes.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
