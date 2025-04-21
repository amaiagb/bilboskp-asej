package com.asej.controller;

import java.io.IOException;  
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Sala;
import com.asej.service.SalaService;

@WebServlet("/listaSalas")
public class ListaSalasController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	SalaService salaService;
	
	@Override
	public void init() throws ServletException {
		salaService = new SalaService();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Sala> listaSalas = salaService.getSalas();
		
		req.getSession().setAttribute("listaSalas", listaSalas);
		
        resp.sendRedirect("/bilboskp-asej/admin/superadmin/listaSalas.jsp");

        
		
	}
}