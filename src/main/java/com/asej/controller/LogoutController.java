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

import com.asej.service.CentroService;
import com.asej.service.CuponService;
import com.asej.service.PartidaService;
import com.asej.service.SuscriptorService;


@WebServlet(name = "logout", urlPatterns = { "/logout" })
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SuscriptorService suscriptorService;   
    CentroService centroService;
    PartidaService partidaService;
    CuponService cuponService;

    public LogoutController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		 suscriptorService = new SuscriptorService();
		 centroService = new CentroService();
		 partidaService = new PartidaService();
		 cuponService = new CuponService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.getSession().invalidate();
		 
		 Cookie[] cookies = request.getCookies();
		 if(cookies != null) {
			 for (Cookie cookie : cookies) {
				if("suscriptor".equals(cookie.getName())) {
					System.out.println(cookie.getMaxAge());
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		 }
		 response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
