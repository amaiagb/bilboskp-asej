package com.asej.controller;

import java.io.IOException; 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Partida;
import com.asej.model.Sala;
import com.asej.service.PartidaService;
import com.asej.service.SalaService;

@WebServlet("/organizar")
public class OrganizarPartidaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PartidaService partidaService;
    SalaService salaService;
    //poner para sacar el id de suscriptor

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        partidaService = new PartidaService();
        salaService = new SalaService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fechaStr = req.getParameter("date");
            String jugadoresStr = req.getParameter("jugadores");
            String descripcion = req.getParameter("descripcion");
            String idSalaStr = req.getParameter("sala");

            if (fechaStr == null || jugadoresStr == null || descripcion == null || idSalaStr == null ||
                fechaStr.isEmpty() || jugadoresStr.isEmpty() || descripcion.isEmpty() || idSalaStr.isEmpty()) {
            	resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime fecha = LocalDateTime.parse(fechaStr, formatter);
            if (fecha.isBefore(LocalDateTime.now())) {
                System.out.println("Fecha no válida: la fecha es en el pasado.");
            	resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            int jugadores = Integer.parseInt(jugadoresStr);
            if (jugadores < 1 || jugadores > 99999) {
                System.out.println("Número de jugadores no válido.");
            	resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            if (descripcion.length() > 100) {
                System.out.println("Descripción demasiado larga.");
            	resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            int idSala = Integer.parseInt(idSalaStr);
            if (idSala <= 0) {
                System.out.println("ID de sala no válido.");
            	resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            Sala sala = new Sala();
            sala.setId_sala(idSala);

            Partida nuevaPartida = new Partida(jugadores, fecha, descripcion, sala);

            if (partidaService.addPartida(nuevaPartida)) {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                System.out.println("Partida creada correctamente.");
            } else {
            	resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                System.out.println("Error al crear la partida.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        	resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
        }
    }
}
