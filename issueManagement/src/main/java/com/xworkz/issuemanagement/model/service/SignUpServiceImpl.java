package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.SignUpRepo;
import com.xworkz.issuemanagement.util.PassWordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SignUpServiceImpl implements SignUpService {




    @Autowired
    private SignUpRepo signUpRepo;

    //password Encrypt
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
   private  EmailService emailService;


 public SignUpServiceImpl()
 {
     System.out.println("creating SignUpServiceImpl");
 }



    @Override
    public boolean validateAndsave(SignUpDTO signUpDTO) {
        System.out.println("Running validateAndsave method");

        //setaudit
        String createdBy = signUpDTO.getFirstName(); // or get the current user
        LocalDateTime createdOn = LocalDateTime.now();
        //String updatedBy = signUpDTO.getFirstName(); // or get the current user
       // LocalDateTime updatedOn = LocalDateTime.now();
        boolean isActive = true;

        setAudit(signUpDTO, createdBy, createdOn, isActive);

        //default image logo
        signUpDTO.setImageName("default.jpeg");

        //generating password stored in database
//        String generatedPassword = PassWordGenerator.generatePassword();
//        signUpDTO.setPassword(generatedPassword);

        String generatedPassword = PassWordGenerator.generatePassword();
      signUpDTO.setPassword(passwordEncoder.encode(generatedPassword));



        boolean save = this.signUpRepo.save(signUpDTO);

        if (save) {
            //password send to email
            signUpDTO.setPassword(generatedPassword);
            emailService.sendSimpleEmail(signUpDTO);

            System.out.println("SignUp is Save successfully in service" + signUpDTO);

        } else {
            System.out.println("SignUp is Not Save Successfully in service" + signUpDTO);

        }
        return true;


    }

    //set audit
    @Override
    public void setAudit(SignUpDTO signUpDTO, String createdBy, LocalDateTime createdOn,boolean isActive) {
        System.out.println("Running setAudit method....");
        signUpDTO.setCreatedBy(createdBy);
        signUpDTO.setCreatedOn(createdOn);
       // signUpDTO.setUpdatedBy(updatedBy);
//        signUpDTO.setUpdatedOn(updatedOn);
        signUpDTO.setActive(isActive);


    }




}





