package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        String nombreRol = request.getParameter("rol");
        
        //Aqui hay que sacar el id_suscriptor de la información de la sesión.
        int id_suscriptor = Integer.parseInt(request.getParameter("id_suscriptor"));
        
        List<Integer> id_cupones = cuponService.createCupones(numeroCupones, nombreRol);
        	
        	if (!id_cupones.isEmpty()) {
        		
        		boolean historialActualizado = true; //Inicializamos a true pero si "actualizarHistorialCupones" es false lo cambiamos a false
        		
        		for (int id_cupon : id_cupones){
        			
        		
		        	if (cuponService.actualizarHistorialCupones(id_suscriptor, id_cupon)) {
		        		
		        		request.setAttribute("numeroCupones", numeroCupones);
		        		request.getRequestDispatcher("private/compraConfirmada.jsp").forward(request, response);
		        	} else {
		        		historialActualizado = false;
		                break;
		        	}
        		}
        	
        	} else {
            
            response.sendRedirect("error.jsp");
        	}	
    }
	
}
