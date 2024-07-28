package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.emailSending.MailSending;
import com.xworkz.issuemanagement.model.service.EmailService;
import com.xworkz.issuemanagement.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
   // private EmailService emailService;
    private MailSending mailSending;

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/sign-up")
    public String signUp(@Valid SignUpDTO signUpDTO, Model model, @RequestParam("email") String email) {
        System.out.println("signUp method running in SignUpController..");
        System.out.println("SignUpDTO ;" + signUpDTO);


            boolean dataValid = this.signUpService.validateAndsave(signUpDTO);
            if(dataValid)
            {
                System.out.println("SignUpService registration successful in SignUpController:"+signUpDTO);
                model.addAttribute("signUpDTO",signUpDTO);

                //email id in dropdown
                httpSession.setAttribute("email", signUpDTO.getEmail());
                //httpSession.setAttribute("image",signUpDTO.getImage());

                return "SignIn";

            }

            else
            {
                System.out.println("SignUpService registration not successful in SignUpController:"+signUpDTO);
            }
            model.addAttribute("message", "SignUp successful :" + signUpDTO.getFirstName()+signUpDTO.getLastName());
        return "SignIn";

    }

}

