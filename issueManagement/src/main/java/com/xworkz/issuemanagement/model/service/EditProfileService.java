package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

import java.time.LocalDateTime;

public interface EditProfileService {


    default SignUpDTO findByEmail(String email)
    {
        return  null;
    }

    //void updateSignupDtoByEmailId(String emailId,SignupDto updatedSignupDto);

    SignUpDTO updateSignupDtoByEmail(SignUpDTO dto);

    void setAudit(SignUpDTO signUpDTO, String updatedBy, LocalDateTime updatedOn);



}
