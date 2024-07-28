package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.ViewProfileSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDTO")
public class ViewProfileController {


    @Autowired
    private ViewProfileSerivce userProfileViewSerivce;


    @Autowired
    private  HttpSession httpSession;


    public ViewProfileController() {
        System.out.println("Creating in UserProfileViewController");
    }

    @GetMapping("/view-profile")
    public String showProfile(Model model) {
        // Assuming you have a method to get the currently logged-in user's email
        String userEmail = userProfileViewSerivce.getLoggedInUserEmail();

        // Fetch user data based on the email
        SignUpDTO signUpDTO = userProfileViewSerivce.getUserByEmail(userEmail);

        // Add the user data to the model
        model.addAttribute("signUpDTO", signUpDTO);

        //httpSession.setAttribute("firstName", signUpDTO.getFirstName()+signUpDTO.getLastName());

        // Return the view name
        return "UserViewProfile";
    }

}

