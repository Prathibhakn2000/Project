package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface ViewProfileSerivce {

    SignUpDTO getUserByEmail(String email);

    String getLoggedInUserEmail();
}
