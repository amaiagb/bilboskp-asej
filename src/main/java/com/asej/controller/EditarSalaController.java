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

@WebServlet("/editarSala")
public class EditarSalaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    SalaService salaService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        salaService = new SalaService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	int idSala = Integer.parseInt(req.getParameter("id_sala"));
            String nombre = req.getParameter("nombre");
            String tipo = req.getParameter("tipo");
           

            if (nombre == null || tipo == null || 
                nombre.isEmpty() || tipo.isEmpty()) {
            	resp.sendRedirect("error.jsp?from=editarSala.jsp");
                return;
            }

            if (nombre.length() > 50) {
                System.out.println("nombre demasiado largo.");
            	resp.sendRedirect("error.jsp?from=editarSala.jsp");
                return;
            }
            
            if (tipo.length() > 50) {
                System.out.println("tipo demasiado largo.");
            	resp.sendRedirect("error.jsp?from=editarSala.jsp");
                return;
            }

            Sala sala = new Sala(nombre, tipo);
            sala.setId_sala(idSala);

            if (salaService.updateSala(sala)) {
                resp.sendRedirect("index.jsp");
                System.out.println("Sala cambiada correctamente.");
            } else {
            	resp.sendRedirect("error.jsp?from=editarSala.jsp");
                System.out.println("Error al editar la sala.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        	resp.sendRedirect("error.jsp?from=editarSala.jsp");
        }
    }
}

