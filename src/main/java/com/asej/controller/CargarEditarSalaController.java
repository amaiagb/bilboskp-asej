package com.asej.controller;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Sala;
import com.asej.service.SalaService;

@WebServlet("/cargarEditarSala")
public class CargarEditarSalaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    SalaService salaService;

    @Override
    public void init() throws ServletException {
        salaService = new SalaService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id_sala");
        

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Sala sala = salaService.getSalaById(id); 
                System.out.println("recogifa de id" + sala.getId_sala());
                if (sala != null) {
                	req.setAttribute("sala", sala);
                	req.getRequestDispatcher("editarSala.jsp").forward(req, resp);

                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("error.jsp");
    }
}

