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
import com.asej.model.Suscriptor;
import com.asej.service.PartidaService;
import com.asej.service.SalaService;

@WebServlet("/organizar")
public class OrganizarPartidaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PartidaService partidaService;
    SalaService salaService;
    String codigo = generarCodigoAleatorio();

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
                System.out.println("Fecha inválida: la fecha es en el pasado.");
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

            // recoge el id de la sesion, creo que esto le he duplicado, pero prefiero asegurarme en cad apaso
            Integer idSuscriptor = (Integer) req.getSession().getAttribute("idSuscriptor");
            if (idSuscriptor == null) {
                System.out.println("No se encontró el suscriptor en la sesión.");
                resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }
            
            

            Sala sala = new Sala();
            sala.setId_sala(idSala);

            String codigo = generarCodigoAleatorio();

            Partida nuevaPartida = new Partida(jugadores, fecha, descripcion, sala);
            boolean success = partidaService.addPartida(nuevaPartida, idSuscriptor, codigo);


            if (success) {
                System.out.println("Partida creada correctamente.");
                resp.sendRedirect(req.getContextPath() + "/admin/organizarPartida.jsp");
            } else {
                System.out.println("Error al crear la partida.");
                resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
        }
    }
    
    //metodo para crear el codigo, lo iba a poner en el service, pero me parece mejor aqui o pot lo menos mas facil pa mi:)
    private String generarCodigoAleatorio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = (int) (Math.random() * caracteres.length());
            codigo.append(caracteres.charAt(index));
        }
        return codigo.toString();
    }


}
