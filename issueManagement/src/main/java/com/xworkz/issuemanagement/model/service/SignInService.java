package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;

public interface SignInService {

    //matching the email and password
    SignUpDTO findByEmailAndPassword(String email, String password);

    //Lock account when give 3 times wrong Password
    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);

    //forgot password
    void unlockAccount(String email);




}
