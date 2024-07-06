package com.xworkz.issuemanagement.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// Sending password to email
@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;

    public EmailServiceImpl()
    {
        System.out.println("Creating EmailServiceImpl");
    }


    @Override
    public void sendSimpleEmail(String toEmail, String subject, String body) {

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom("prathibhakn2000@gmail.com");

            mailSender.send(message);
        }
    }



