package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.model.repository.ResetPasswordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private ResetPasswordRepo resetPasswordRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        System.out.println("Running resetPassword");

        if (!resetPasswordRepo.emailExists(email)) {
            System.out.println("Email does not exist");
            return false;
        }

        if (!resetPasswordRepo.verifyOldPassword(email, oldPassword)) {
            System.out.println("Old password does not match");
            return false;
        }

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New password and confirm password do not match");
            return false;
        }

        resetPasswordRepo.updatePassword(email, newPassword);
        sendPasswordEmail(email, "Password Reset Successful", "Your password has been successfully reset. Your new password is: " + newPassword);

        return true;
    }

    @Override
    public void sendPasswordEmail(String toEmail, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom("prathibhakn2000@gmail.com");
        javaMailSender.send(simpleMailMessage);
    }
}
