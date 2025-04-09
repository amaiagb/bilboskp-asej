package com.asej.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Suscriptor;
import com.asej.service.SuscriptorService;


@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SuscriptorService suscriptorService;   

    public LoginController() {
        super();
    }


	public void init(ServletConfig config) throws ServletException {
		 suscriptorService = new SuscriptorService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. Recoger datos del form del login
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		
		//2. Crear instancia de Suscriptor con los datos introducidos
		Suscriptor suscriptor = new Suscriptor();
		suscriptor.setUsuario(usuario);
		suscriptor.setContrasena(contrasena);
		
		//3. Comprobar en la BD si existe un usuario con esos datos
		Suscriptor suscriptorLogin = suscriptorService.login(suscriptor);
		System.out.println(suscriptorLogin);
		
		//3.1. Si la consulta devuelve un suscriptor -> Login correcto
		if(suscriptorLogin != null) {
			
			//4. Guardarlo en un atributo y redirigir
			System.out.println("usuario en la base de datos");
			request.getSession().setAttribute("suscriptor", suscriptorLogin);
			System.out.println(request.getSession().getAttribute("suscriptor"));
			request.getRequestDispatcher("private/index.jsp").forward(request, response);
		} else {
			//3.2 Si devuelve un valor null -> no existe esa combinación de usuario y contrasena
			response.sendRedirect("login.jsp?error=1");
		}
		
	}

}
