package com.xworkz.issuemanagement.controller;

import com.xworkz.issuemanagement.dto.DepartmentDTO;
import com.xworkz.issuemanagement.dto.EmployeeDTO;
import com.xworkz.issuemanagement.emailSending.EmployeeOTPSending;
import com.xworkz.issuemanagement.model.service.AdminService;
import com.xworkz.issuemanagement.model.service.EmployeeService;
import com.xworkz.issuemanagement.util.OTPGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {


    public EmployeeController()
    {
        System.out.println("Creating EmployeeController");
    }

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private AdminService adminService;


    @Autowired
    private EmployeeOTPSending employeeOTPSending;


    @Autowired
    private OTPGenerator otpGenerator;



    @PostMapping("/employee-singup")
    public String signUp(@Valid EmployeeDTO employeeDTO, RedirectAttributes redirectAttributes) {
        System.out.println("signUp method running in EmployeeController..");

        DepartmentDTO dataValid = this.adminService.searchByDeptName(employeeDTO.getDepartmentType());
        System.out.println("dataValid" +dataValid);
        employeeDTO.setDeptId(dataValid);

            boolean employeedetail=this.employeeService.validateAndSaveEmployeeDetails(employeeDTO);
            if(employeedetail) {
                System.out.println("SignUpService registration successful in EmployeeController:" + employeeDTO);
                redirectAttributes.addFlashAttribute("message", "Register successful :" + employeeDTO.getEmpFullName());
            }
            else
            {
            System.out.println("SignUpService registration not successful in EmployeeController:"+employeeDTO);
                redirectAttributes.addFlashAttribute("message", "Not Register :" + employeeDTO.getEmpFullName());
            }

        return "redirect:/view-department-in-employee";

    }

    @GetMapping("/view-department-in-employee")
    public String getform(Model model){
        List<DepartmentDTO> departments = adminService.getAllDepartments();
        model.addAttribute("departments",departments);
        return "EmployeeSignUp";
    }





    //employee login
    // Employee Login with CAPTCHA validation
    // Employee Login with CAPTCHA and OTP generation

    @PostMapping("generateOtp")
    public String generateOtp(@RequestParam("email") String email,
                              @RequestParam("captcha") String captchaInput,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        System.out.println("generateOtp method running in EmployeeController..");

        // Store email in session
        session.setAttribute("email", email);

        // Check if the email exists in the database
        EmployeeDTO employeeDTO = employeeService.findByEmail(email);
        if (employeeDTO == null) {
            redirectAttributes.addFlashAttribute("emailNotFound", "Email does not exist.");
            System.out.println("Email not found in the database: " + email);
            return "redirect:/employeeLogin";
        }

        // Validate CAPTCHA
        String sessionCaptcha = (String) session.getAttribute("captcha");
        if (sessionCaptcha == null || !sessionCaptcha.equals(captchaInput)) {
            redirectAttributes.addFlashAttribute("captchaError", "Invalid CAPTCHA.");
            System.out.println("Invalid CAPTCHA for email: " + email);
            return "redirect:/employeeLogin";
        }

        // CAPTCHA is valid, proceed with OTP generation
        String otp = otpGenerator.generateOtp();
        System.out.println("OTP generated: " + otp);

        // Save OTP in employeeDTO and update in the database
        employeeDTO.setOtp(Long.parseLong(otp));
        boolean isSaved = employeeService.validateAndSaveEmployeeDetails(employeeDTO);

        if (isSaved) {
            // Send OTP to the user's email
            employeeOTPSending.sendOtpEmail(employeeDTO.getEmail(), otp);
            redirectAttributes.addFlashAttribute("generatedOTP", "OTP generated and sent to email.");
            System.out.println("OTP generated and sent to email: " + email);
            return "redirect:/employee-otp-page";
        } else {
            redirectAttributes.addFlashAttribute("failedToGenerateOTPError", "Failed to generate OTP, please try again.");
            System.out.println("Failed to save OTP for email: " + email);
        }

        return "redirect:/employeeLogin"; // Redirect back to login page if there is an error
    }

    @GetMapping("employee-otp-page")
    public String employeeOtpPage() {
        return "EmployeeOTPSend"; // Display OTP entry page
    }

    @PostMapping("validateOtp")
    public String validateOtp(@RequestParam("email") String email,
                              @RequestParam("otp") String otpInput,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        System.out.println("validateOtp method running in EmployeeController..");

        // Check if the email exists in the database
        EmployeeDTO employeeDTO = employeeService.findByEmail(email);
        if (employeeDTO == null) {
            redirectAttributes.addFlashAttribute("emailNotFound", "Email does not exist.");
            System.out.println("Email not found in the database: " + email);
            return "redirect:/employeeLogin";
        }

        // Validate OTP
        if (employeeDTO.getOtp() == null || !employeeDTO.getOtp().toString().equals(otpInput)) {
            redirectAttributes.addFlashAttribute("otpError", "Invalid OTP.");
            System.out.println("Invalid OTP for email: " + email);
            return "redirect:/employeeProfile";
        }

        // OTP is valid, redirect to employee profile
        System.out.println("OTP validated for email: " + email);
        return "redirect:/employeeProfile";
    }

    @GetMapping("employeeLogin")
    public String employeeLoginPage() {
        return "EmployeeLogIn"; // Display login page
    }

    @GetMapping("employeeProfile")
    public String employeeProfilePage() {
        return "EmployeeProfile"; // Display employee profile page
    }


    @PostMapping("/resendOtp")
    public String resendOtp(HttpSession session,
                            RedirectAttributes redirectAttributes) {
        // Retrieve email from session
        String email = (String) session.getAttribute("email");

        if (email == null || email.isEmpty()) {
            redirectAttributes.addFlashAttribute("emailNotFound", "Email not found in session.");
            return "redirect:/employeeLogin";
        }

        System.out.println("resendOtp method running in EmployeeController..");

        // Check if the email exists in the database
        EmployeeDTO employeeDTO = employeeService.findByEmail(email);
        if (employeeDTO == null) {
            redirectAttributes.addFlashAttribute("emailNotFound", "Email does not exist.");
            System.out.println("Email not found in the database: " + email);
            return "redirect:/employeeLogin";
        }

        // Generate a new OTP
        String otp = otpGenerator.generateOtp();
        System.out.println("OTP generated: " + otp);

        // Save new OTP in employeeDTO and update in the database
        employeeDTO.setOtp(Long.parseLong(otp));
        boolean isSaved = employeeService.validateAndSaveEmployeeDetails(employeeDTO);

        if (isSaved) {
            // Send OTP to the user's email
            employeeOTPSending.sendOtpEmail(employeeDTO.getEmail(), otp);
            redirectAttributes.addFlashAttribute("generatedOTP", "New OTP generated and sent to email.");
            System.out.println("New OTP generated and sent to email: " + email);
            return "redirect:/employee-otp-page";
        } else {
            redirectAttributes.addFlashAttribute("failedToGenerateOTPError", "Failed to generate OTP, please try again.");
            System.out.println("Failed to save OTP for email: " + email);
        }

        return "redirect:/employeeLogin"; // Redirect back to login page if there is an error
    }

}


