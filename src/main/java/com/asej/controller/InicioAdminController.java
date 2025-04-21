package com.asej.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.service.CentroService;
import com.asej.service.CuponService;
import com.asej.service.PartidaService;
import com.asej.service.SalaService;
import com.asej.service.SuscriptorService;


@WebServlet("/inicioAdmin")
public class InicioAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SuscriptorService suscriptorService;   
    CentroService centroService;
    PartidaService partidaService;
    CuponService cuponService;
    SalaService salaService;

    public InicioAdminController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		 suscriptorService = new SuscriptorService();
		 centroService = new CentroService();
		 partidaService = new PartidaService();
		 cuponService = new CuponService();
		 salaService = new SalaService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		//  Numero de Suscriptores
		
		int numSuscriptores = suscriptorService.countSuscriptores();
		request.getSession().setAttribute("numSuscriptores", numSuscriptores);
		//request.setAttribute("numSuscriptores", numSuscriptores);
		
		//  Numero de Centros
		
		int numCentros = centroService.countCentros();
		request.getSession().setAttribute("numCentros", numCentros);
		//request.setAttribute("numCentros", numCentros);
		
		//  Numero de Solicitudes Pendientes
		
		int numSolicitudes = centroService.countSolicitudes();
		request.getSession().removeAttribute("numSolicitudes");
		request.getSession().setAttribute("numSolicitudes", numSolicitudes);
		//request.setAttribute("numSolicitudes", numSolicitudes);
		
		// Numero de Salas Disponibles
		int numSalas = salaService.countSalas();
		System.out.println(numSalas);
		request.getSession().removeAttribute("numSalas");
		request.getSession().setAttribute("numSalas", numSalas);
		
		/*
		//Para el gráfico
		HashMap<String, Integer> datos = new HashMap<>();
		datos.put("Suscriptores", numSuscriptores);
		datos.put("Centros", numCentros);
		
		request.setAttribute("datos", datos);
		*/
		//request.getRequestDispatcher("admin/index.jsp").forward(request, response);
		response.sendRedirect("/bilboskp-asej/admin/superadmin/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		
		
		
	}

}
