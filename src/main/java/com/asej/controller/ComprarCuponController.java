package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Suscriptor;
import com.asej.service.CuponService;

@WebServlet("/comprarCupon")

public class ComprarCuponController extends HttpServlet{
	
	CuponService cuponService;
	
	@Override
	public void init() throws ServletException {
		
		this.cuponService = new CuponService();
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
        int numeroCupones = Integer.parseInt(request.getParameter("numeroCupones"));
        
        
        
        //Aqui hay que sacar el id_suscriptor de la informaci�n de la sesi�n.
       // int id_suscriptor = Integer.parseInt(obtenerIdSuscriptorDesdeCookie(request));
        Suscriptor suscriptor = (Suscriptor) request.getSession().getAttribute("suscriptor");
        
        String nombreRol = suscriptor.getRol().getNombre();
        
        int id_suscriptor = suscriptor.getId();
        
        List<Integer> id_cupones = cuponService.createCupones(numeroCupones, nombreRol);
        	
        	if (!id_cupones.isEmpty()) {
        		
        		boolean historialActualizado = true; //Inicializamos a true pero si "actualizarHistorialCupones" es false lo cambiamos a false
        		
        		for (int id_cupon : id_cupones){
        			
        		
		        	if (cuponService.actualizarHistorialCupones(id_suscriptor, id_cupon)) {
		        		
		        		
		        	} else {
		        		historialActualizado = false;
		                break;
		        	}
        		}
        		
        		if (historialActualizado == true) {
        			request.setAttribute("numeroCupones", numeroCupones);
	        		//response.sendRedirect("private/compraConfirmada.jsp");
	        		request.getRequestDispatcher("/admin/compraConfirmada.jsp").forward(request, response);
        		} else {
        			response.sendRedirect("error.jsp");
        		}
        	
        	} else {
        		response.sendRedirect("error.jsp");
        	}
    }
	
}
