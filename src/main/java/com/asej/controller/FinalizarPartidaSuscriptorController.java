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
import com.asej.model.Sala;
import com.asej.model.Suscriptor;
import com.asej.service.CuponService;
import com.asej.service.PartidaService;
import com.asej.service.SalaService;

@WebServlet("/finalizarPartidaSuscriptor")
public class FinalizarPartidaSuscriptorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PartidaService partidaService;
    private CuponService cuponService;
    private SalaService salaService; 

    public FinalizarPartidaSuscriptorController() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        partidaService = new PartidaService();
        cuponService = new CuponService();
        salaService = new SalaService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String salaNombre = (String) session.getAttribute("salaSeleccionada");
        Suscriptor suscriptor = (Suscriptor) request.getSession().getAttribute("suscriptor");
        Integer id_suscriptor = suscriptor.getId();

        if (salaNombre != null && id_suscriptor != null) {
            Sala sala = salaService.obtenerSalaPorNombre(salaNombre); 

            if (sala != null) {
            	System.out.println("aaaaaaaaaaaaaaaah");
                Partida nuevaPartida = new Partida();
                nuevaPartida.setSala(sala);  
                nuevaPartida.setPuntuacion(new Random().nextInt(10000));  
                nuevaPartida.setEstado("finalizada");

                boolean partidaCreada = partidaService.crearPartidaSuscriptor(nuevaPartida, id_suscriptor);

                if (partidaCreada) {
                	System.out.println("bbbbbbbbbbbbbbbbbbbbbb");
                    session.removeAttribute("salaSeleccionada");  
                    cuponService.setCuponUsado(); 
                    response.sendRedirect("/bilboskp-asej/inicio");
                } else {
                    response.sendRedirect("error.jsp");
                }
            } else {
                response.sendRedirect("error.jsp");  
            }
        } else {
            response.sendRedirect("index.jsp"); 
        }
    }
}
