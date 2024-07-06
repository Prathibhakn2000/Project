package com.xworkz.issuemanagement.model.service;

public interface EmailService {

    // Sending password to email
    void sendSimpleEmail(String toEmail, String subject,String body);
}
