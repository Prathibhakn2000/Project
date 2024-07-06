package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.SignInService;
import com.xworkz.issuemanagement.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class SignInController {

    public SignInController() {
        System.out.println("Creating SignInController");
    }

    @Autowired
    private SignUpService signUpService;

    //signIn
    @Autowired
    private SignInService signInService;


    @PostMapping("sign-in")
    public String signin(@RequestParam String email, @RequestParam String password, Model model) {
        System.out.println("Running signin method...");
        //System.out.println("Dto Details:" + signinFormDto);

        SignUpDTO signUpDTO = signInService.findByEmailAndPassword(email, password);
        if (signUpDTO != null) {
            signInService.resetFailedAttempts(email);
            model.addAttribute("msg1", "successfully logined with:" + signUpDTO.getFirstName() + signUpDTO.getLastName());
            return "SignIn";
        } else {
            signInService.incrementFailedAttempts(email);
            int failedAttempts = signInService.getFailedAttempts(email);
            System.out.println("Failed attempts for " + email + ": " + failedAttempts);


            if (failedAttempts >= 3) {
                signInService.lockAccount(email); // Lock account after 3 failed attempts
                model.addAttribute("error", "Your account is locked due to too many failed attempts.");
                model.addAttribute("accountLocked", true);
            } else {
                model.addAttribute("error", "Invalid email address and password. Attempts: " + failedAttempts);
                model.addAttribute("accountLocked", false);
            }
            //model.addAttribute("error", "Invalid email id and password");
            return "SignIn";
        }
    }
}




