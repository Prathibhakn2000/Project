package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.RaiseComplaintDTO;
import com.xworkz.issuemanagement.dto.SignUpDTO;
import com.xworkz.issuemanagement.model.service.RaiseComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class RaiseComplaintController {



        @Autowired
        private RaiseComplaintService raiseComplaintService;

        public RaiseComplaintController() {
            System.out.println("Creating RaiseComplaintController...");
        }

        @PostMapping("raise-complaint")
        public String raiseComplaint(RaiseComplaintDTO raiseComplaintDTO, RedirectAttributes redirectAttributes, SignUpDTO signUpDTO) {
            System.out.println("raiseComplaint method running in RaiseComplaintController..");


            boolean dataValid = raiseComplaintService.saveRaiseComplaintData(raiseComplaintDTO);

            if (dataValid) {
                System.out.println("RaiseComplaintService registration successful in RaiseComplaintController.");
                redirectAttributes.addFlashAttribute("raiseComplaintMsg", "RaiseComplaint Registration Successful: " + raiseComplaintDTO.getComplaintId());
            } else {
                System.out.println("RaiseComplaintService registration not successful in RaiseComplaintController..");
                redirectAttributes.addFlashAttribute("raiseComplaintMsg", "RaiseComplaint Registration failed");
            }


            return "redirect:/raise-complaint";
        }

       @GetMapping("raise-complaint")
        public String showRaiseComplaintPage(Model model) {
           return "RaiseComplaint";
}
   }

