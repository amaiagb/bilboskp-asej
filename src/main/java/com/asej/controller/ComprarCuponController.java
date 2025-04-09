package com.asej.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.service.CuponService;

@WebServlet("/comprarCupon")

public class ComprarCuponController extends HttpServlet{
	
	CuponService cuponService;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
        int numeroCupones = Integer.parseInt(request.getParameter("numeroCupones"));
        
        String nombreRol = request.getParameter("rol");
        
        if (cuponService.createCupones(numeroCupones, nombreRol)) {
        	
	        	if (cuponService.actualizarHistorialCupones(numeroCupones, numeroCupones, numeroCupones)) {
	        		
	        		request.setAttribute("numeroCupones", numeroCupones);
	        		request.getRequestDispatcher("compraConfirmada.jsp").forward(request, response);
	        	}
        	
        } else {
            
            response.sendRedirect("error.jsp");
        }
    }
	
}
