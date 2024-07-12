package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface ViewProfileRepo {

    SignUpDTO findByEmail(String email);
}
