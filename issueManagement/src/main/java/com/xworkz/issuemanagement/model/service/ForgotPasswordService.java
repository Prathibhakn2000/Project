package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import org.springframework.stereotype.Service;


public interface ForgotPasswordService  {
    boolean forgotPassword(String email);
}
