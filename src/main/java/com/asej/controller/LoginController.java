package com.asej.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asej.model.Centro;
import com.asej.model.Cupon;
import com.asej.model.Partida;
import com.asej.model.Suscriptor;
import com.asej.service.CentroService;
import com.asej.service.CuponService;
import com.asej.service.PartidaService;
import com.asej.service.SuscriptorService;


@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    SuscriptorService suscriptorService;   
    CentroService centroService;
    PartidaService partidaService;
    CuponService cuponService;

    public LoginController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		 suscriptorService = new SuscriptorService();
		 centroService = new CentroService();
		 partidaService = new PartidaService();
		 cuponService = new CuponService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. Recoger datos del form del login
		
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		
		//2. Crear instancia de Suscriptor con los datos introducidos
		
		Suscriptor suscriptor = new Suscriptor();
		suscriptor.setUsuario(usuario);
		suscriptor.setContrasena(contrasena);
		
		//3. Comprobar en la BD si existe un usuario con esos datos
		
		Suscriptor suscriptorLogin = suscriptorService.login(suscriptor);
		System.out.println("login: "+suscriptorLogin);
		
		//3.1. Si la consulta devuelve un suscriptor -> Login correcto
		
		if(suscriptorLogin != null) {
			
			//4. Comprobar el rol que tiene el suscriptor
			
			//4.1 Es un suscriptor de tipo centro
			if("centro".equals(suscriptorLogin.getRol().getNombre())) {
				Centro centro = centroService.getCentroBySuscriptor(suscriptorLogin);
				
				//  A�adir centro a la sesi�n
				request.getSession().setAttribute("suscriptor", centro);
				System.out.println("centro: "+request.getSession().getAttribute("suscriptor"));
				
			} //4.2 Es un suscriptor est�ndar
			else if("suscriptor".equals(suscriptorLogin.getRol().getNombre())) {
				
				// A�adir suscriptor a la sesi�n
				request.getSession().setAttribute("suscriptor", suscriptorLogin);
				System.out.println("suscriptor: "+request.getSession().getAttribute("suscriptor"));
				
			} //4.3 Es un admin
			else if ("admin".equals(suscriptorLogin.getRol().getNombre())) {
				
				// A�adir admin a la sesi�n
				request.getSession().setAttribute("suscriptor", suscriptorLogin);
				System.out.println("admin: "+request.getSession().getAttribute("suscriptor"));
				response.sendRedirect("/bilboskp-asej/admin/superadmin/admin.jsp");
				return;
			}
			//5.Obtener info de partidas y cupones del suscriptor
			
			List<Partida> partidas = partidaService.getPartidasById(suscriptorLogin.getId());
			request.getSession().setAttribute("partidas", partidas);
			
			List<Cupon> cupones = cuponService.getCupones(suscriptorLogin.getId());
			request.getSession().setAttribute("cupones", cupones);
			
			//6.Obtener Ultimas partidas jugadas
			
			List<Partida> ultimasPartidas = partidaService.getUltimasPartidasJugadasById(suscriptorLogin.getId());
			request.getSession().setAttribute("ultimasPartidas", ultimasPartidas);
			System.out.println("att ultimas partidas: "+request.getSession().getAttribute("ultimasPartidas"));
			
			//7. Crear cookie de suscriptor
			
			Cookie cookie = new Cookie("suscriptor", suscriptorLogin.getUsuario());
			cookie.setMaxAge(60*60*24*7);
			response.addCookie(cookie);
			
			response.sendRedirect("admin/index.jsp");
			
		} else {
			//3.2 Si devuelve un valor null -> no existe esa combinaci�n de usuario y contrasena
			
			response.sendRedirect("login.jsp?error=1");
		}
		
	}

}
