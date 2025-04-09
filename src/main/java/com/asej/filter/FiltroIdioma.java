package com.asej.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroIdioma implements Filter {

    public FiltroIdioma() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
	        HttpSession session = req.getSession();

	        String langParam = req.getParameter("lang");
	        if (langParam != null && (langParam.equals("es") || langParam.equals("en"))) {
	            session.setAttribute("lang", langParam);
	        } else if (session.getAttribute("lang") == null) {
	            session.setAttribute("lang", "es");
	        }

	        chain.doFilter(request, response);
	    }

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
	

}
