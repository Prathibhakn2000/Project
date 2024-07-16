package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.SignInService;
import com.xworkz.issuemanagement.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;




@Controller
@RequestMapping("/")

public class SignInController {

    private static Logger log= LogManager.getLogger(SignInController.class);


    public SignInController() {
        System.out.println("Creating SignInController");
        log.info("controller");


    }

    @Autowired
    private SignUpService signUpService;

//    @Autowired
//    private HttpSession httpSession;

    //signIn
    @Autowired
    private SignInService signInService;


    @PostMapping("sign-in")
    public String signin(@RequestParam String email, @RequestParam String password, Model model, HttpServletRequest request) {
        System.out.println("Running signin method...");
        //System.out.println("Dto Details:" + signinFormDto);

        SignUpDTO signUpDTO = signInService.findByEmailAndPassword(email, password);
        if (signUpDTO != null) {
            signInService.resetFailedAttempts(email);
            model.addAttribute("msg1", "successfully logined with:" + signUpDTO.getFirstName() + signUpDTO.getLastName());

            HttpSession httpSession=request.getSession();
            //  view (set sessionfor email)
            httpSession.setAttribute("SignedInUserEmail",email);

            //update
            httpSession.setAttribute("signUpDTO",signUpDTO);



            //Set the profile image in the session
            //image display in profile
            String profileImageUrl = "/images/" + signUpDTO.getImageName();
            System.out.println(signUpDTO.getImageName());
            System.out.println(signUpDTO);
            httpSession.setAttribute("profileImage", profileImageUrl);


            return "Profile";




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




