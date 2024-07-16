
package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.EditProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
@SessionAttributes("signUpDTO") // Specify the model attribute to be stored in session
public class EditProfileController {

    @Autowired
    private EditProfileService editProfileService;

    @Autowired
    private HttpSession httpSession;



    @GetMapping("/edit-profile")
    public String showUserDetails(Model model) {
        SignUpDTO signUpDTO = (SignUpDTO) httpSession.getAttribute("signUpDTO");
        if (signUpDTO != null) {
            model.addAttribute("signUpDTO", signUpDTO);

            // Set the signed-in user email in session if not already set
            if (httpSession.getAttribute("signedInUserEmail") == null) {
                httpSession.setAttribute("signedInUserEmail", signUpDTO.getFirstName());
            }


            System.out.println("Signed-in user email: " + signUpDTO.getEmail());
        } else {
            System.out.println("User email not found in session.");
            // Handle session expired or not signed in
        }
        return "UserEditProfile"; // Return the name of your JSP file
    }

    @PostMapping("/edit-profile")
    public String editUser(SignUpDTO signUpDTO, Model model) {
        SignUpDTO updatedUser = editProfileService.updateSignupDtoByEmail(signUpDTO);
        if (updatedUser != null) {
            model.addAttribute("signUpDTO", updatedUser);
            model.addAttribute("profileMessage", "Profile updated successfully");
            return "UserEditProfile";
        }
        model.addAttribute("profileError", "Error updating profile");
        return "Profile";
    }
}

