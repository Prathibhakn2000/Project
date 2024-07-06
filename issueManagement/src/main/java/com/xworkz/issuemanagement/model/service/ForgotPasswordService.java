package com.xworkz.issuemanagement.model.service;

import org.springframework.stereotype.Service;


public interface ForgotPasswordService  {
    boolean forgotPassword(String email);
}
