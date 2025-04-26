package com.asej.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/descargarJuego")
public class DescargarJuegoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String googleDriveFileId = "1eYXuqEw5a6C07dhpTUu_x40oNXC0ed6A"; 

        String downloadLink = "https://drive.google.com/uc?export=download&id=" + googleDriveFileId;

        response.sendRedirect(downloadLink);
    }
}
