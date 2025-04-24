package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Centro;
import com.asej.model.Cupon;
import com.asej.service.CentroService;
import com.asej.service.CuponService;
import com.asej.service.RolService;
import com.asej.service.SuscriptorService;

@WebServlet(name = "cancelarCentro", urlPatterns = { "/cancelarCentro" })
public class BajaCentroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CentroService centroService; 
	SuscriptorService suscriptorService;
	RolService rolService;
	CuponService cuponService;
	
    public BajaCentroController() {
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
		
		// 1. Guardar id_centro
		int id_centro = Integer.parseInt(request.getParameter("id_centro"));
		Centro centro = centroService.getCentroById(id_centro);
		
		// 2. Obtener id_suscriptor de ese centro 
		int id_suscriptor = centro.getId();
		
		if(id_centro != 0 && id_suscriptor != 0) {
		
			// Obtener todos los cupones disponibles y programados para devolverlos y calcula el total de euros
			 List<Cupon> cupones = cuponService.getCuponesDevolviblesCentro(id_suscriptor);
			 double totalReembolso = cupones.stream().mapToDouble(Cupon::getPrecio).sum();
			 
			//Dentro del service se realizan todos los deletes necesarios
			if(centroService.deleteCentroCompleto(centro, totalReembolso)) {
				
				// 5 Redirigir
				//response.sendRedirect("/bilboskp-asej/listaCentros");
				request.setAttribute("reembolso", true);
				request.setAttribute("totalReembolso", totalReembolso);  
				request.setAttribute("email", centro.getEmail());  

				request.getRequestDispatcher("/admin/superadmin/listaCentros.jsp").forward(request, response);
				
			} else {
				//Error en el delete
				response.sendRedirect("/bilboskp-asej/admin/error.jsp?from=deleteCentro");
			}
			
		} else {
			//No es suscriptor -> no se puede borrar
		}
		
	}
}
