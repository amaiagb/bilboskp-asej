package com.asej.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Suscriptor;
import com.asej.service.CentroService;
import com.asej.service.CuponService;
import com.asej.service.RolService;
import com.asej.service.SuscriptorService;

@WebServlet(name = "cancelarSuscriptor", urlPatterns = { "/cancelarSuscriptor" })
public class BajaSuscriptorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CentroService centroService; 
	SuscriptorService suscriptorService;
	RolService rolService;
	CuponService cuponService;
	
    public BajaSuscriptorController() {
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
		
		// 1. Guardar id_suscriptor
		int id_suscriptor = Integer.parseInt(request.getParameter("id_suscriptor"));
		System.out.println(id_suscriptor);
		
		// 2. Confirmar en la BD que es un suscriptor y no un centro
		Suscriptor suscriptor = suscriptorService.getSuscriptorById(id_suscriptor);
		System.out.println(suscriptor);
		
		if("suscriptor".equals(suscriptor.getRol().getNombre())) {
		
			//Dentro del service se realizan todos los deletes necesarios
			if(suscriptorService.deleteSuscriptor(id_suscriptor)) {
				
				// 5 Redirigir
				response.sendRedirect("/bilboskp-asej/listaSuscriptores");
				
			} else {
				//Error en el delete
				response.sendRedirect("/bilboskp-asej/admin/error.jsp");
			}
			
		} else {
			//No es suscriptor -> no se puede borrar
		}
		
	}
}
