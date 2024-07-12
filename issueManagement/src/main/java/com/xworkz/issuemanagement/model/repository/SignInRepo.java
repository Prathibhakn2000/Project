package com.xworkz.issuemanagement.model.repository;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface SignInRepo {

    //// Sending password to email and Signin
    SignUpDTO findByEmailAndPassword(String email, String password);

    //Lock account when give 3 times wrong Password
    SignUpDTO findByEmail(String email); // Add this method to find a user by email


    boolean update(SignUpDTO signUpDto);
}
