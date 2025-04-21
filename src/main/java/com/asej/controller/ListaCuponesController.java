package com.asej.controller;

import java.io.IOException;  
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Cupon;
import com.asej.model.Suscriptor;
import com.asej.service.CuponService;

@WebServlet("/listaCupones")
public class ListaCuponesController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	CuponService cuponService;
	
	@Override
	public void init() throws ServletException {
		cuponService = new CuponService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Recuperar de sesi�n
		Suscriptor suscriptor = (Suscriptor) req.getSession().getAttribute("suscriptor");
        
        int id_suscriptor = suscriptor.getId();
        
		List<Cupon> listaCupones = cuponService.getCupones(id_suscriptor);
		
//		req.setAttribute("listaCupones", listaCupones);
//		System.out.println(listaCupones);
//		
//        req.getRequestDispatcher("admin/listaCupones.jsp").forward(req, resp);
		
		req.getSession().setAttribute("listaCupones", listaCupones);
		
		resp.sendRedirect("admin/listaCupones.jsp");
            

        
		
	}
}