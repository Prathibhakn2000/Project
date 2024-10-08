package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.SignInRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SignInServiceImpl implements  SignInService{
 public SignInServiceImpl()
 {
     System.out.println("Creating SignInServiceImpl");
 }
 @Autowired
 private SignInRepo signInRepo;

 @Autowired
 private  PasswordEncoder passwordEncoder;

 //Signin
    @Override
    public SignUpDTO findByEmailAndPassword(String email, String password) {
        //password encrypt
        SignUpDTO signUpDTO1  =this.signInRepo.findByEmailID(email);

       // SignUpDTO signUpDTO = signInRepo.findByEmailAndPassword(email, password);

        if (signUpDTO1 != null && passwordEncoder.matches(password,signUpDTO1.getPassword()) && !signUpDTO1.isAccountLocked()) {
            System.out.println("signUpDTO1"+signUpDTO1);
           return signUpDTO1;

        }
        return null;
        //return user;
    }

    //Lock account when give 3 times wrong Password
    @Override
    public void incrementFailedAttempts(String email) {
        SignUpDTO user = signInRepo.findByEmail(email);
        if (user != null) {
            int attempts = user.getFailedAttempt() + 1;
            user.setFailedAttempt(attempts);
            if (attempts >= 3) {
                user.setAccountLocked(true);
            }
            signInRepo.update(user);
        }

    }

    @Override
    public int getFailedAttempts(String email) {
        SignUpDTO user = signInRepo.findByEmail(email);
        return (user != null) ? user.getFailedAttempt() : 0;
    }

    @Override
    public void resetFailedAttempts(String email) {
        SignUpDTO user = signInRepo.findByEmail(email);
        if (user != null)
        {
            user.setFailedAttempt(0); //false
            signInRepo.update(user);
        }

    }

    @Override
    public void lockAccount(String email) {
        SignUpDTO user = signInRepo.findByEmail(email);
        if (user != null) {
            user.setAccountLocked(true);
            signInRepo.update(user);
        }

    }

    //forgot password
    @Override
    public void unlockAccount(String email) {
        SignUpDTO user = signInRepo.findByEmail(email);
        if (user != null) {
            user.setAccountLocked(false);
            signInRepo.update(user);
        }

    }


}

