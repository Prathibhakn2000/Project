package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.repository.ResetPasswordRepo;
import com.xworkz.issuemanagement.model.repository.SignInRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private ResetPasswordRepo resetPasswordRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SignInRepo signInRepo;

    @Override
    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        System.out.println("Running resetPassword");

//        if (!resetPasswordRepo.emailExists(email)) {
//            System.out.println("Email does not exist");
//            return false;
//        }
//
//        if (!resetPasswordRepo.verifyOldPassword(email, oldPassword)) {
//            System.out.println("Old password does not match");
//            return false;
//        }
//
//        if (!newPassword.equals(confirmPassword)) {
//            System.out.println("New password and confirm password do not match");
//            return false;
//        }
//
//        resetPasswordRepo.updatePassword(email, newPassword);
//        sendPasswordEmail(email, "Password Reset Successful", "Your password has been successfully reset. Your new password is: " + newPassword);
//
//        return true;
//    }

        // Step 1: Check if newPassword matches confirmPassword
        if (!newPassword.equals(confirmPassword)) {
            System.out.println("New password and confirm password do not match.");
            return false;
        }

        // Step 2: Retrieve SignUpDTO based on email
        SignUpDTO signUpDTO = signInRepo.findByEmailID(email);
        if (signUpDTO == null) {
            System.out.println("User with email " + email + " not found.");
            return false; // User not found
        }

        String storedPassword = signUpDTO.getPassword();
        System.out.println(storedPassword);

        // Step 3: Verify oldPassword matches the stored password
        if (!passwordEncoder.matches(oldPassword, storedPassword)) {
            System.out.println("Old password verification failed for email: " + email);
            return false; // Old password doesn't match
        }

        // Step 4: Encode and update the new password in SignUpDTO
        signUpDTO.setPassword(passwordEncoder.encode(newPassword));

        // Step 5: Save the updated password in the repository
        boolean save = resetPasswordRepo.updatePassword(email, signUpDTO.getPassword());

        // Step 6: Send email notification if password update was successful
        if (save) {
            System.out.println("Password updated successfully for email: " + email);
            try {
                String subject = "Password Reset Confirmation";
                String body = "Your password has been successfully reset.";
                sendPasswordEmail(email, subject, body);
                return true; // Password successfully updated and email sent
            } catch (MailException e) {
                // Handle exception if email sending fails (log it or take appropriate action)
                e.printStackTrace();
                return false; // Indicate failure if email sending failed
            }
        }

        return false; // Password update failed
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