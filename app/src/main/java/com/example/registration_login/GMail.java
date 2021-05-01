package com.example.registration_login;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class GMail {


    final String emailPort = "587";// gmail's smtp port
    final String smtpAuth = "true";
    final String starttls = "true";
    final String emailHost = "smtp.gmail.com";

    String fromEmail;
    String fromPassword;
    @SuppressWarnings("rawtypes")
    String toEmail;
    String emailSubject;
    String emailBody;

    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;

    public GMail(String fromEmail, String fromPassword,
                 String toEmail, String emailSubject, String emailBody) {
        this.fromEmail = fromEmail;
        this.fromPassword = fromPassword;
        this.toEmail = toEmail;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;

        emailProperties=new Properties();
        emailProperties.put("mail.smtp.port", emailPort);
        emailProperties.put("mail.smtp.auth", smtpAuth);
        emailProperties.put("mail.smtp.starttls.enable", starttls);
        emailProperties.put("mail.smtp.host",emailHost);

    }

    public MimeMessage createEmailMessage()  {
        mailSession =Session.getInstance(emailProperties,
                new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail,fromPassword);
                    }
                });

        try {
            emailMessage = new MimeMessage(mailSession);

            emailMessage.setFrom(new InternetAddress(fromEmail));

            emailMessage.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(toEmail));


            emailMessage.setSubject(emailSubject);
            emailMessage.setText(emailBody);
            return emailMessage;
        }
        catch(MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void sendEmail() throws AddressException, MessagingException {

        Transport transport = mailSession.getTransport("smtp");
        transport.connect(emailHost, fromEmail, fromPassword);
        transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
        transport.close();

    }
}
