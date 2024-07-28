package com.xworkz.issuemanagement.model.service;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailSending.MailSending;
import com.xworkz.issuemanagement.model.repository.ResetPasswordRepo;
import com.xworkz.issuemanagement.model.repository.SignInRepo;
import com.xworkz.issuemanagement.model.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private ResetPasswordRepo resetPasswordRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SignInRepo signInRepo;

    @Autowired
    private MailSending mailSending;

    @Override
    public boolean resetPassword(String email, String oldPassword, String newPassword, String confirmPassword) {
        System.out.println("Running resetPassword");

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
                mailSending.sendresetPassword(signUpDTO, newPassword);
                return true; // Password successfully updated and email sent
            } catch (MailException e) {
                // Handle exception if email sending fails (log it or take appropriate action)
                e.printStackTrace();
                return false; // Indicate failure if email sending failed
            }
        }

        return false; // Password update failed
    }
}
