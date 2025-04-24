package com.asej.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asej.model.Partida;
import com.asej.service.PartidaService;

@WebServlet("/finalizarPartida")
public class FinalizarPartidaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PartidaService partidaService;

    public FinalizarPartidaController() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        partidaService = new PartidaService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Partida partida = (Partida) session.getAttribute("partida");

        if (partida != null) {
            int puntuacion = new Random().nextInt(10000); 

            partida.setPuntuacion(puntuacion);
            partida.setEstado("finalizada");

            boolean actualizada = partidaService.actualizarPartida(partida);

            if (actualizada) {
            	//esto es lo que dije en la reunion de quitarlo de la sesion, para que no buguuee, creo que con esto sirve
                session.removeAttribute("partida");

                response.sendRedirect("/bilboskp-asej/admin/index.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } else {
        	//pongo esto, pues no se a donde mandar en caos contrario, vicva lo abstracto
            response.sendRedirect("index.jsp");
        }
    }
}
