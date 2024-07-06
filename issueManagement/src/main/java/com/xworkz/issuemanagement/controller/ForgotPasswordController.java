package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.model.service.ForgotPasswordService;
import com.xworkz.issuemanagement.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ForgotPasswordController {


        public ForgotPasswordController() {
            System.out.println("Creating ForgotPasswordController");
        }

        @Autowired
        private ForgotPasswordService forgotPasswordService;
    //Forgot  password

    @PostMapping("forgot-password")
    public String forgotPassword(@RequestParam String email, Model model) {
        boolean success = forgotPasswordService.forgotPassword(email);
        if (success) {
            model.addAttribute("resetMessage", "A new password has been sent to your email.");
        } else {
            model.addAttribute("resetError", "Email address not found.");
            return "ForgotPassword";
        }

        return "SignIn";
    }

}
