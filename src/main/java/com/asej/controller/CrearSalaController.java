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

import com.asej.model.Sala;
import com.asej.service.SalaService;

@WebServlet("/crearSala")
public class CrearSalaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    SalaService salaService;
    //poner para sacar el id de suscriptor

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        salaService = new SalaService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nombre = req.getParameter("nombre");
            String tipo = req.getParameter("tipo");
            String estado = req.getParameter("estado");
           System.out.println(nombre);
           System.out.println(tipo);
           System.out.println(estado);

            if (nombre == null || tipo == null || 
                nombre.isEmpty() || tipo.isEmpty()) {
            	resp.sendRedirect("error.jsp?from=crearSala.jsp");
                return;
            }

            if (nombre.length() > 50) {
                System.out.println("nombre demasiado largo.");
            	resp.sendRedirect("error.jsp?from=crearSala.jsp");
                return;
            }
            
            if (tipo.length() > 50) {
                System.out.println("tipo demasiado largo.");
            	resp.sendRedirect("error.jsp?from=crearSala.jsp");
                return;
            }

            Sala nuevaSala = new Sala(nombre, tipo, estado);

            if (salaService.addSala(nuevaSala)) {
                resp.sendRedirect("/bilboskp-asej/listaSalas");
                System.out.println("Sala creada correctamente.");
            } else {
            	resp.sendRedirect("error.jsp?from=crearSala.jsp");
                System.out.println("Error al crear la sala.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        	resp.sendRedirect("error.jsp?from=crearSala.jsp");
        }
    }
}

