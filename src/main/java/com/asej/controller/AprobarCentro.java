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

@WebServlet(name = "aprobarCentro", urlPatterns = { "/aprobarCentro" })
public class AprobarCentro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CentroService centroService; 
	SuscriptorService suscriptorService;
	RolService rolService;
	CuponService cuponService;
	
    public AprobarCentro() {
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
		
		//2 Actualizar BD estado: pendiente -> aprobado
		if(centroService.updateEstadoCentro(id_centro)) {
			
			//3. Obtener la info del centro
			Centro centro = centroService.getCentroById(id_centro);
			
			// 4. Crear cupones = numAlumnado 
			List<Integer> id_cupones = cuponService.createCupones(centro.getNum_alumnado(), "centro");
			if(id_cupones.size() > 0) {
				
				// 5. Insertar los cupones en el historial
				for (Integer id_cupon : id_cupones) {
					cuponService.actualizarHistorialCupones(centro.getId(), id_cupon);
				}
			}
			// 6. Redirigir
			// Si el btn estaba en la pagina listaCentros.jsp
			if("lista".equalsIgnoreCase(request.getParameter("origen"))) {
				response.sendRedirect("/bilboskp-asej/listaCentros");
			} 
			// Si el btn estaba en la pagina listaCentrosPendientes.jsp 
			else {
				response.sendRedirect("/bilboskp-asej/centrosPendientes");
			}
		}
	}
}
