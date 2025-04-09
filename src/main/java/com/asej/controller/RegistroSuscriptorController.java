package com.asej.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Suscriptor;
import com.asej.service.SuscriptorService;

@WebServlet(name = "registro", urlPatterns = { "/registro" })
public class RegistroSuscriptorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SuscriptorService suscriptorService;   
	
    public RegistroSuscriptorController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		suscriptorService = new SuscriptorService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. Recoger datos del formulario
		String nombre = request.getParameter("nombre");
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		String email = request.getParameter("email");
		
		//2. Crear instancia
		Suscriptor nuevoSuscriptor = new Suscriptor(nombre, usuario, contrasena, email);
		nuevoSuscriptor.setFecha_alta(LocalDate.now());
		
		//3. Comprobar si hay un suscriptor en la BD con ese usuario
		/*
		if(suscriptorService.getSuscriptorByUsuario(usuario) != null) {
			
			
		}
		*/
		
		//4. Insertar en la BD
		if(suscriptorService.addSuscriptor(nuevoSuscriptor) > 0) {
			
			request.getSession().setAttribute("suscriptor", nuevoSuscriptor);
			request.getRequestDispatcher("private/index.jsp").forward(request, response);
			
		} else {
			
			
			
		}
		
	}

}
