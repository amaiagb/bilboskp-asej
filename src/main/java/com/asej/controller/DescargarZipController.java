package com.asej.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/descargarZip")
public class DescargarZipController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ruta del archivo ZIP en el directorio webapp/game
        String archivoRuta = getServletContext().getRealPath("/game/escaperoom.rar");

        File archivo = new File(archivoRuta);

        if (archivo.exists()) {
            // Establecer los encabezados para la descarga
            response.setContentType("application/zip");
            response.setContentLength((int) archivo.length());
            response.setHeader("Content-Disposition", "attachment; filename=\"juegoAsej.rar\"");
            
            // Leer el archivo y enviarlo al cliente
            try (InputStream in = new FileInputStream(archivo);
                 OutputStream out = response.getOutputStream()) {
                 
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            }
        } else {
            response.getWriter().write("El archivo no existe.");
        }
    }
}

