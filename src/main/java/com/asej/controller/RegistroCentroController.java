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
import com.asej.model.Suscriptor;
import com.asej.service.CentroService;
import com.asej.service.SuscriptorService;

@WebServlet(name = "registroCentro", urlPatterns = { "/registroCentro" })
public class RegistroCentroController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CentroService centroService;   
	
    public RegistroCentroController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		centroService = new CentroService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("nombre");
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		String email = request.getParameter("email");

		LocalDate fecha_alta =LocalDate.now();
		
		String nombre_centro = request.getParameter("nombre_centro");
		String localidad = request.getParameter("localidad");
		int etapas_educativas = Integer.valueOf(request.getParameter("etapas_educativas"));
		int num_alumnado = Integer.valueOf(request.getParameter("num_alumnado"));
		
		Centro nuevoCentro = new Centro(nombre, usuario, contrasena, email, nombre_centro, localidad, etapas_educativas, num_alumnado);
		nuevoCentro.setFecha_alta(fecha_alta);
		
		if(centroService.addCentro(nuevoCentro)) {
			
			System.out.println("centro en la base de datos");
			request.getSession().setAttribute("suscriptor", nuevoCentro);
			System.out.println(request.getSession().getAttribute("suscriptor"));
			request.getRequestDispatcher("private/index.jsp").forward(request, response);
			
		} else {
			
			
			
		}
		
	}

}
