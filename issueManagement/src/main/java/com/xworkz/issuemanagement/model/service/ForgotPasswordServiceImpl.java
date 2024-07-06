package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repo.ForgotpasswordRepo;
import com.xworkz.issuemanagement.util.PassWordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

@Repository
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
   private ForgotpasswordRepo forgotpasswordRepo;

    @Autowired
    private SignInService signInService;  //unlockAccount

    @Autowired
    private JavaMailSender javaMailSender; //EmailConfig




    @Override
    public boolean forgotPassword(String email) {
        System.out.println("resetPassword method running in ForgotPasswordServiceImpl");

        SignUpDTO user = forgotpasswordRepo.findByEmail(email);
        if (user != null) {
            String newPassword = PassWordGenerator.generatePassword();
            forgotpasswordRepo.updatePassword(email, newPassword);

            // Reset failed attempts
            signInService.resetFailedAttempts(email);
            signInService.unlockAccount(email);


            SimpleMailMessage message = new SimpleMailMessage(); //Simplemail message is a cls
            message.setTo(email);
            message.setSubject("Password Reset");
            message.setText("Your new password is: " + newPassword);
            javaMailSender.send(message);

            return true;
        }
        return false;
    }
}

