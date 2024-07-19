package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.EmailService;
import com.xworkz.issuemanagement.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class SignUpController {

    public SignUpController()
    {
        System.out.println("Creating SignUpController");
    }

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/sign-up")
    public String signUp(@Valid SignUpDTO signUpDTO, Model model, @RequestParam("email") String email) {
        System.out.println("signUp method running in SignUpController..");
        System.out.println("SignUpDTO ;" + signUpDTO);


            boolean dataValid = this.signUpService.validateAndsave(signUpDTO);
            if(dataValid)
            {
                System.out.println("SignUpService registration successful in SignUpController:"+signUpDTO);
                model.addAttribute("signUpDTO",signUpDTO);

                return "SignIn";

            }

            else
            {
                System.out.println("SignUpService registration not successful in SignUpController:"+signUpDTO);
            }
            model.addAttribute("msg", "Registration successful :" + signUpDTO.getFirstName()+signUpDTO.getLastName());
        return "SignIn";

    }

}

