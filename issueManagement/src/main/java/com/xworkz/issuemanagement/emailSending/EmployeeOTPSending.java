package com.xworkz.issuemanagement.emailSending;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmployeeOTPSending {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        System.out.println("OTP is generated for you mail");
        message.setText("Your One Time Password (OTP) for login : " + otp);
        mailSender.send(message);
    }
}
