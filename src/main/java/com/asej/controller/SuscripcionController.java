package com.asej.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "suscripcion", urlPatterns = { "/suscripcion" })
public class SuscripcionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SuscripcionController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("suscripcion.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
