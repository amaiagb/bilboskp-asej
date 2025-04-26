package com.asej.controller;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MandarMailController {

    public static void mandarCorreo(String destinatario, String asunto, String mensaje) throws MessagingException {
        final String remitente = "paquito27891@gmail.com"; 
        final String contrasena = "csjvpmbksejgfuzk"; 

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtptarttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Esto se usa para crear la sesion del mail y proteger la proia contraseña utilizada para el envio
        Session session = Session.getInstance(props,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remitente, contrasena); 
                }
            });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);
            System.out.println("Correo enviado a: " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
