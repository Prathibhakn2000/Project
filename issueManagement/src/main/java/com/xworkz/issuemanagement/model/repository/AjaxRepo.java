package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface AjaxRepo {

    //Avoid duplicate emails and contact no using Ajax

    SignUpDTO existByEmailId(String email);

    SignUpDTO existByContactNumber(Long contactNumber);
}
