package com.xworkz.issuemanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("signup-page")
    public String signUpPage()
    {
        return "SignUp";
    }


    @GetMapping("signin-page")
    public String signInPage()
    {
        return "SignIn";
    }
}
