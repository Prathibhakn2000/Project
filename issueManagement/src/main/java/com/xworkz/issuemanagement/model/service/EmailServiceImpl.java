package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// Sending password to email
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public EmailServiceImpl() {
        System.out.println("Creating EmailServiceImpl");
    }


    @Override
    public void sendSimpleEmail(SignUpDTO signUpDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(signUpDTO.getEmail());
        message.setSubject("One Time Password");
        message.setText("Dear " + signUpDTO.getFirstName() + " " + signUpDTO.getLastName() + ", You have been successfully Signed Up,\n\n" +
                "Please Sign in through this password: " + signUpDTO.getPassword() + "\n\n" +
                "Thanks and Regards,\n" + " " +
                "XworkzProject Team");
        mailSender .send(message);
    }
    }




