package com.asej.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Partida;
import com.asej.service.PartidaService;
import com.asej.service.SalaService;

@WebServlet("/cargarEditarPartida")
public class CargarEditarPartidaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PartidaService partidaService;

    @Override
    public void init() throws ServletException {
        partidaService = new PartidaService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id_partida");
        

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Partida partida = partidaService.getPartidaById(id); 

                if (partida != null) {
                	SalaService salaService = new SalaService();
                	req.setAttribute("salas", salaService.getSalas());
                	req.setAttribute("partida", partida);
                	req.getRequestDispatcher("editarPartida.jsp").forward(req, resp);

                    return;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("error.jsp");
    }
}

