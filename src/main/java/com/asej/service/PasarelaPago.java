package com.asej.service;

public class PasarelaPago {

	 public boolean procesarReembolso(String emailContacto, double cantidad) {
	        System.out.println("Simulando reembolso...");
	        System.out.println("Reembolsando " + cantidad + "� a " + emailContacto);
	        
	        // Simulaci�n de �xito de reembolso
	        return true;
	    }
}
