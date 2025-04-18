package com.asej.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Centro;
import com.asej.model.Suscriptor;
import com.asej.service.CentroService;
import com.asej.service.SuscriptorService;

@WebFilter("/superadmin/*")
public class AdminFilter implements Filter {
	
	SuscriptorService suscriptorService;
	CentroService centroService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		suscriptorService = new SuscriptorService();
		centroService = new CentroService();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//recoger las cookies
		Cookie[] cookies = req.getCookies();
		
		//buscar la cookie usuario
		for(Cookie c : cookies) {
			
			if("suscriptor".equals(c.getName())) {
				//si existe, buscar el usuario en la BD
				Suscriptor s = suscriptorService.getSuscriptorByUsuario(c.getValue());
				
				//comprobar si tiene rol de admin
				if("admin".equals(s.getRol().getNombre())) {
					
					//añadir a la sesion
					req.getSession().setAttribute("suscriptor", s);
					
				}				
			}
		}

		if(req.getSession().getAttribute("suscriptor")!=null) {
			chain.doFilter(request, response);
		}else {
			res.sendRedirect("../login.jsp");
		}
	}
	
	@Override
	public void destroy() {}

}