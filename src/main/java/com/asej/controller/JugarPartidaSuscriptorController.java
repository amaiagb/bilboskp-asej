package com.asej.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/jugarPartidaSuscriptor")
public class JugarPartidaSuscriptorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String salaSeleccionada = request.getParameter("sala");
        
        if (salaSeleccionada != null) {
            session.setAttribute("salaSeleccionada", salaSeleccionada);
        }
        
        response.sendRedirect("/bilboskp-asej/admin/jugarPartidaSuscriptor.jsp");
    }
}
