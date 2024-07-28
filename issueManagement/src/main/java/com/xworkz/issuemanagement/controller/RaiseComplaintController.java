package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.RaiseComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDTO")
public class RaiseComplaintController {


    @Autowired
    private RaiseComplaintService raiseComplaintService;

    @Autowired
    private HttpSession httpSession;


    public RaiseComplaintController() {
        System.out.println("Creating RaiseComplaintController...");
    }

    @PostMapping("raise-complaint")
    public String raiseComplaint(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO, @ModelAttribute("raiseComplaintDTO") RaiseComplaintDTO raiseComplaintDTO, Model model) {

        System.out.println("Running raiseComplaint method in RaiseComplaintController...");
        // Accessing id from SignupDto
        int signedInUserId = signUpDTO.getId();
        System.out.println("Signed in user ID: " + signedInUserId);

        // Set the signed in user ID in raiseComplaintDto
        SignUpDTO userDto = new SignUpDTO();
        userDto.setId(signedInUserId);
        raiseComplaintDTO.setSignUpDTO(userDto);

        boolean save=raiseComplaintService.saveRaiseComplaintType(raiseComplaintDTO);


        if(save)
        {
            System.out.println("Controller:save raiseComplaint details successfully"+raiseComplaintDTO);
            model.addAttribute("raiseComplaintSucess","saved raiseComplaint details successfully");

            httpSession.setAttribute("firstName", signUpDTO.getFirstName());
            return "RaiseComplaint";
        }

        else {
            model.addAttribute("ErrorRaiseComplaintSucess"," Not saved raiseComplaint details successfully");
            System.out.println("Controller:not save raiseComplaint details successfully"+raiseComplaintDTO);
        }
        return "RaiseComplaint";
    }


//    @GetMapping("raise-complaint")
//    public String showRaiseComplaintPage(Model model) {
//        return "RaiseComplaint";
//    }


    //view RaiseComplaint
    @GetMapping("view-complaint")
    public String viewRaiseComplaint(Model model, @ModelAttribute("signUpDTO") SignUpDTO signUpDTO) {
        int userId = signUpDTO.getId();
        List<RaiseComplaintDTO> complaints = raiseComplaintService.getComplaintsByUserId(userId);
        model.addAttribute("viewRaiseComplaints", complaints);
        return "ViewComplaint";
    }

    @GetMapping("/edit-complaint/{complaintId}")
    public String showEditComplaintForm(@PathVariable("complaintId") int complaintId, Model model) {
        RaiseComplaintDTO raiseComplaintDTO = raiseComplaintService.getComplaintById(complaintId);
        model.addAttribute("raiseComplaintDTO", raiseComplaintDTO);//values should be retain in page
        return "EditComplaint";
    }


    //update

    @PostMapping("/update-complaint")
    public String updateComplaint(@ModelAttribute("raiseComplaintDTO") RaiseComplaintDTO raiseComplaintDTO, Model model) {
       List<RaiseComplaintDTO> isUpdated= raiseComplaintService.updateRaiseComplaintUserDetails(raiseComplaintDTO);
        if (isUpdated!=null) {
            model.addAttribute("updateMsg", "Complaint updated successfully!");
            model.addAttribute("viewRaiseComplaints",isUpdated);
            return "ViewComplaint";
        } else {
            model.addAttribute("updateMsg", "Failed to update complaint. Please try again.");
        }
        return "EditComplaint";
    }
}
