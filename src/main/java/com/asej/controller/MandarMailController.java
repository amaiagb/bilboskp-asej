package com.asej.controller;

import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MandarMailController {

    public static void mandarCorreo(String destinatario, String asunto, String mensaje) throws MessagingException {
        final String remitente = "paquito27891@gmail.com"; 
        final String contrasena = "eyzafoiwuzwpjcet"; 

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable", "true"); 
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "587"); 

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("paquito27891@gmail.com", "eyzafoiwuzwpjcet");
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
