package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Centro;
import com.asej.model.Cupon;
import com.asej.model.Partida;
import com.asej.model.Suscriptor;
import com.asej.service.CentroService;
import com.asej.service.CuponService;
import com.asej.service.PartidaService;
import com.asej.service.SuscriptorService;


@WebServlet("/inicio")
public class InicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SuscriptorService suscriptorService;   
    CentroService centroService;
    PartidaService partidaService;
    CuponService cuponService;

    public InicioController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		 suscriptorService = new SuscriptorService();
		 centroService = new CentroService();
		 partidaService = new PartidaService();
		 cuponService = new CuponService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. Obtener suscriptor de la sesion 
		
				Suscriptor suscriptor = (Suscriptor) request.getSession().getAttribute("suscriptor"); 
				
				//2. Obtener datos a mostrar en el index desde la BD
				
				//2.1 Numero de Cupones disponibles
				
				List<Cupon> cuponesDisponibles = cuponService.getCuponesDisponibles(suscriptor.getId());
				request.getSession().setAttribute("cuponesDisponibles", cuponesDisponibles);
				System.out.println("inicio: "+request.getSession().getAttribute("cuponesDisponibles"));
				
				//2.2 Partidas jugadas ordenadas por mas reciente
				
				List<Partida> ultimasPartidas = partidaService.getUltimasPartidasJugadasById(suscriptor.getId());
				request.getSession().setAttribute("ultimasPartidas", ultimasPartidas);
				System.out.println("inicio: "+request.getSession().getAttribute("ultimasPartidas"));
				
				//2.3 Numero de Partidas programadas
				
				int numPartidasProgramadas = partidaService.getNumPartidasProgramadas(suscriptor.getId());
				request.getSession().setAttribute("numPartidasProgramadas", numPartidasProgramadas);
				System.out.println("inicio: "+request.getSession().getAttribute("numPartidasProgramadas"));
					
				response.sendRedirect("admin/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		
		
		
	}

}
