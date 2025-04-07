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
		
		//System.out.println(LocalDate.now());
		String nombre = request.getParameter("nombre");
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		String email = request.getParameter("email");
		
		Suscriptor nuevoSuscriptor = new Suscriptor(nombre, usuario, contrasena, email);
		nuevoSuscriptor.setFecha_alta(LocalDate.now());
		
		if(suscriptorService.addSuscriptor(nuevoSuscriptor)) {
			
			System.out.println("usuario en la base de datos");
			request.getSession().setAttribute("suscriptor", suscriptorLogin);
			System.out.println(request.getSession().getAttribute("suscriptor"));
			request.getRequestDispatcher("private/index.jsp").forward(request, response);
			
		} else {
			
			
			
		}
		
	}

}
