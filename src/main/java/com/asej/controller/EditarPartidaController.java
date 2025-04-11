package com.asej.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Sala;
import com.asej.model.Partida;
import com.asej.service.PartidaService;

@WebServlet("/editarPartida")
public class EditarPartidaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PartidaService partidaService;

    @Override
    public void init() throws ServletException {
        partidaService = new PartidaService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idPartidaStr = req.getParameter("id_partida"); 
            String fechaStr = req.getParameter("date");
            String jugadoresStr = req.getParameter("jugadores");
            String descripcion = req.getParameter("descripcion");
            String idSalaStr = req.getParameter("sala");

            if (idPartidaStr == null || fechaStr == null || jugadoresStr == null ||
                descripcion == null || idSalaStr == null ||
                idPartidaStr.isEmpty() || fechaStr.isEmpty() || jugadoresStr.isEmpty() ||
                descripcion.isEmpty() || idSalaStr.isEmpty()) {
                resp.sendRedirect("error.jsp?from=listaPartidas.jsp");
                return;
            }

            int idPartida = Integer.parseInt(idPartidaStr); 
            if (idPartida <= 0) {
                resp.sendRedirect("error.jsp?from=listaPartidas.jsp");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime fecha = LocalDateTime.parse(fechaStr, formatter);
            if (fecha.isBefore(LocalDateTime.now())) {
                resp.sendRedirect("error.jsp?from=listaPartidas.jsp");
                return;
            }

            int jugadores = Integer.parseInt(jugadoresStr);
            if (jugadores < 1 || jugadores > 99999) {
                resp.sendRedirect("error.jsp?from=listaPartidas.jsp");
                return;
            }

            if (descripcion.length() > 100) {
                resp.sendRedirect("error.jsp?from=listaPartidas.jsp");
                return;
            }

            int idSala = Integer.parseInt(idSalaStr);
            if (idSala <= 0) {
                resp.sendRedirect("error.jsp?from=listaPartidas.jsp");
                return;
            }

            Sala sala = new Sala();
            sala.setId_sala(idSala);

            Partida partida = new Partida(jugadores, fecha, descripcion, sala);
            partida.setId_partida(idPartida); 

            if (partidaService.updatePartida(partida)) {
                resp.sendRedirect("listaPartidas");
            } else {
                resp.sendRedirect("error.jsp?from=listaPartidas.jsp");
            }


        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp?from=listaPartidas.jsp");
        }
    }
}

