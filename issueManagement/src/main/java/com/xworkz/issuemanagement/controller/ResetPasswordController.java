package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetPasswordService;

    public ResetPasswordController() {
        System.out.println("Creating ResetPasswordController...");
    }

    @PostMapping("reset-password")
    public String passwordReset(Model model,
                                @RequestParam("email") String email,
                                @RequestParam("oldpassword") String oldPassword,
                                @RequestParam("newpassword") String newPassword,
                                @RequestParam("confirmpassword") String confirmPassword) {

        boolean resetSuccessful = resetPasswordService.resetPassword(email, oldPassword, newPassword, confirmPassword);
        if (resetSuccessful) {
            System.out.println("Password reset Successful: " + resetSuccessful);
            model.addAttribute("passwordResetMessage", "Password reset successful");
        } else {
            model.addAttribute("passwordResetError", "Failed to reset password. Please check your password");
        }

        //return "ResetPassword";
        return "SignIn";
    }
}
