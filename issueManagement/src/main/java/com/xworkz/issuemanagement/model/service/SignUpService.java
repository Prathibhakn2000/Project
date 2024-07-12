package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.time.LocalDateTime;

public interface SignUpService {

    //validate and save and signup
    boolean validateAndsave(SignUpDTO signUpDTO);

    //setaudit
    void setAudit(SignUpDTO signUpDTO, String createdBy, LocalDateTime createdOn,boolean isActive);


}
