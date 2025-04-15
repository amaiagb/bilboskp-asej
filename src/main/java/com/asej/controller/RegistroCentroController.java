package com.asej.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Centro;
import com.asej.model.Rol;
import com.asej.service.CentroService;
import com.asej.service.RolService;
import com.asej.service.SuscriptorService;

@WebServlet(name = "registroCentro", urlPatterns = { "/registroCentro" })
public class RegistroCentroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CentroService centroService; 
	SuscriptorService suscriptorService;
	RolService rolService;
	
    public RegistroCentroController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		centroService = new CentroService();
		suscriptorService = new SuscriptorService();
		rolService = new RolService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. Recoger datos del formulario
		
		String nombre = request.getParameter("nombre");
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		String email = request.getParameter("email");
		Rol rol = rolService.getRolByNombre("centro");
		LocalDate fecha_alta =LocalDate.now();
		String nombre_centro = request.getParameter("nombre_centro");
		String localidad = request.getParameter("localidad");
		int etapas_educativas = Integer.parseInt(request.getParameter("etapas_educativas"));
		int num_alumnado = Integer.parseInt(request.getParameter("num_alumnado"));
		
		//2. Crear instancia de Centro 
		
		Centro nuevoCentro = new Centro(nombre, usuario, contrasena, email, nombre_centro, localidad, etapas_educativas, num_alumnado);
		nuevoCentro.setRol(rol);
		nuevoCentro.setFecha_alta(fecha_alta);
		
		//3. Comprobar que el suscriptor ni el centro está en la BD
		if(suscriptorService.getSuscriptorByUsuario(usuario) != null & centroService.getCentroByName(nombre_centro) != null) {
			
			//3.1 ERROR -> Redirigir ?error=1 ya existe en la BD
			
		}
		
		//4. Insertar nuevoSuscriptor (Persona de contacto del centro) en tabla suscriptores y devolver su id
		
		int id_suscriptor = suscriptorService.addSuscriptor(nuevoCentro);
		System.out.println(id_suscriptor);
		nuevoCentro.setId(id_suscriptor);
		
		//5. Insertar nuevoCentro en tabla centro
		
		if(centroService.addCentro(nuevoCentro)) {
			
			//6. Inserción correcta -> Redirigir a index
			request.getSession().setAttribute("suscriptor", nuevoCentro);
			response.sendRedirect("bilboskp-asej/admin/index.jsp");
			
		} else {
			//7. Inserción incorrecta -> Borrar Suscriptor y Redirigir a registro?error=1
			
			
		}
		
	}

}
