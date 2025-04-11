package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.service.CuponService;

@WebServlet("/devolverCupon")

public class DevolverCuponController extends HttpServlet{
	
	CuponService cuponService;
	
	@Override
	public void init() throws ServletException {
		
		this.cuponService = new CuponService();
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
       
        
        //Aqui hay que sacar el id_suscriptor de la información de la sesión.
        int id_cupon = Integer.parseInt(request.getParameter("id_cupon"));       
               	
        	if (id_cupon != 0) {
        		
        		cuponService.returnCupon(id_cupon);
        		response.sendRedirect("listaCupones");
        		 	
        	} else {
            
            response.sendRedirect("error.jsp");
        	}	
    }
	
}
