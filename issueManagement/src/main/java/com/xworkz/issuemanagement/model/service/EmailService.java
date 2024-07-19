package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface EmailService {

    // Sending password to email
    void sendSimpleEmail(SignUpDTO signUpDTO);
}
