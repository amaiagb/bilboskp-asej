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

@WebFilter("/admin/*")
public class AuthFilter implements Filter {
	
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
				//si es un suscriptor estandar
				if("suscriptor".equals(s.getRol().getNombre())) {
					//añadir a la sesion
					req.getSession().setAttribute("suscriptor", s);
					req.getSession().setAttribute("tipoSuscriptor", "suscriptor");
				}//si es un suscriptor de tipo centro
				else if("centro".equals(s.getRol().getNombre())) {
					//recuperar datos del centro
					Centro centro = centroService.getCentroBySuscriptor(s);
					//añadir a la sesion
					req.getSession().setAttribute("suscriptor", centro);
					req.getSession().setAttribute("tipoSuscriptor", "centro");
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