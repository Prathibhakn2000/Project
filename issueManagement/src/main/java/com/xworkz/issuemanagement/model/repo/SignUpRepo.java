package com.xworkz.issuemanagement.model.repo;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface SignUpRepo {

    //saving data
    boolean save(SignUpDTO signUpDTO);
}
