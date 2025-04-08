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
		
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		
		Suscriptor suscriptor = new Suscriptor();
		suscriptor.setUsuario(usuario);
		suscriptor.setContrasena(contrasena);
		
		Suscriptor suscriptorLogin = suscriptorService.login(suscriptor);
		System.out.println(suscriptorLogin);
		
		if(suscriptorLogin != null) {
			System.out.println("usuario en la base de datos");
			request.getSession().setAttribute("suscriptor", suscriptorLogin);
			System.out.println(request.getSession().getAttribute("suscriptor"));
			request.getRequestDispatcher("private/index.jsp").forward(request, response);
		} else {
			response.sendRedirect("login.jsp?error=1");
		}
		
	}

}
