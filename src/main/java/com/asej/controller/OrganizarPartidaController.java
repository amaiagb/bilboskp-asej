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
import javax.servlet.http.HttpSession;

import com.asej.model.Partida;
import com.asej.model.Sala;
import com.asej.service.CuponService;
import com.asej.model.Suscriptor;
import com.asej.service.PartidaService;
import com.asej.service.SalaService;
import com.asej.controller.MandarMailController; 

@WebServlet("/organizar")
public class OrganizarPartidaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    PartidaService partidaService;
    SalaService salaService;
    CuponService cuponService;
    String codigo = generarCodigoAleatorio();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        partidaService = new PartidaService();
        salaService = new SalaService();
        cuponService = new CuponService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String fechaStr = req.getParameter("date");
            String jugadoresStr = req.getParameter("jugadores");
            String descripcion = req.getParameter("descripcion");
            String idSalaStr = req.getParameter("sala");

            int numCupones = Integer.parseInt(jugadoresStr);

            if (fechaStr == null || jugadoresStr == null || descripcion == null || idSalaStr == null ||
                fechaStr.isEmpty() || jugadoresStr.isEmpty() || descripcion.isEmpty() || idSalaStr.isEmpty()) {
                resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime fecha = LocalDateTime.parse(fechaStr, formatter);

            if (fecha.isBefore(LocalDateTime.now())) {
                resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            int jugadores = Integer.parseInt(jugadoresStr);
            if (jugadores < 1 || jugadores > 99999) {
                resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            if (descripcion.length() > 100) {
                resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            int idSala = Integer.parseInt(idSalaStr);
            if (idSala <= 0) {
                resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            Integer idSuscriptor = (Integer) req.getSession().getAttribute("idSuscriptor");
            if (idSuscriptor == null) {
                resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
                return;
            }

            boolean existePartida = partidaService.existePartidaEnFecha(fecha);
            if (existePartida) {
                req.setAttribute("errorFecha", "Ya existe una partida creada para ese día.");
                resp.sendRedirect(req.getContextPath() + "/admin/organizarPartida.jsp");
                return;
            }

            Sala sala = new Sala();
            sala.setId_sala(idSala);

            String codigo = generarCodigoAleatorio();

            Partida nuevaPartida = new Partida(jugadores, fecha, descripcion, sala);
            boolean success = partidaService.addPartida(nuevaPartida, idSuscriptor, codigo);

            if (success) {
                cuponService.programarCupon(numCupones);

                HttpSession session = req.getSession();
                Suscriptor suscriptor = (Suscriptor) session.getAttribute("suscriptor");

                if (suscriptor != null) {
                    String destinatario = suscriptor.getEmail();
                    String asunto = "Partida organizada exitosamente";
                    String mensaje = "La partida ha sido organizada correctamente.\n\n" +
                                     "Fecha: " + fechaStr + "\n" +
                                     "Jugadores: " + jugadoresStr + "\n" +
                                     "Descripción: " + descripcion + "\n" +
                                     "Sala: " + sala + "\n" +
                                     "Código de la partida: " + codigo;

                    MandarMailController.mandarCorreo(destinatario, asunto, mensaje);
                }

                resp.sendRedirect(req.getContextPath() + "/admin/organizarPartida.jsp");
            } else {
                resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp?from=organizarPartida.jsp");
        }
    
    }

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
